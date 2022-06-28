package test1.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.*;

import java.io.IOException;
import java.util.*;

/**
 * @author EricBruneau
 *
 *         Player de la grille et des accords uniquement cliqués
 * 
 */
@WebServlet("/PlayerMidi")
public class PlayerMidi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Sequencer sequencer;
	private Sequence sequence;
	private Integer tempo;
	private Integer compte = 0;
	private Track track, trackBasse;

	public PlayerMidi() {
		super();
		this.tempo = 60;
	}

	public PlayerMidi(Integer leTempo) {
		super();
		this.tempo = leTempo;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("grike", Grille.maGrille);

		PlayerMidi SophiaMidi = new PlayerMidi();
		SophiaMidi.injectSeq(Grille.maGrille);
		SophiaMidi.injectBasse(Grille.maGrille);
		SophiaMidi.play();

		request.setAttribute("longGrille", Grille.maGrille.getLength());

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("longGrille", Grille.maGrille.getLength());
		doGet(request, response);
	}

	/**
	 * Injection de la note vers l'objet msgOn et msgOff de type MidiEvent
	 * 
	 * @param track
	 * @param played
	 * @param valeurNote
	 * @param tempo
	 * @param _time
	 * @param newPos
	 * @param timming
	 */
	public void putANote(Track track, boolean played, int valeurNote, Integer tempo, Long _time, long newPos,
			Integer timming) {
		Integer velocite = played ? 100 : 0;
		try {

			MidiEvent msgOn = this.makeEvent(144, 1, valeurNote + 48, velocite,
					Math.round(newPos * (60 * 4 / tempo) + 60 * 4 / tempo));
			MidiEvent msgOff = this.makeEvent(128, 1, valeurNote + 48, velocite,
					Math.round((newPos + 8 / timming) * (60 * 4 / tempo) + 60 * 4 / tempo));
			track.add(msgOn);
			track.add(msgOff);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Création de la grille sous forme d'une suites d'événements midi
	 * 
	 * @param command
	 * @param channel
	 * @param note
	 * @param velocity
	 * @param tick
	 * 
	 * @return événemetn midi à jouer
	 */
	public MidiEvent makeEvent(int command, int channel, int note, int velocity, int tick) {

		MidiEvent event = null;

		try {

			// ShortMessage stores a note as command type, channel,
			// instrument it has to be played on and its speed.
			ShortMessage a = new ShortMessage();
			a.setMessage(command, channel, note, velocity);

			// A midi event is comprised of a short message(representing
			// a note) and the tick at which that note has to be played
			event = new MidiEvent(a, tick);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return event;
	}

	public void stopPlay(Sequencer sequencer) {
		this.sequencer.stop();
		this.sequencer.close();
	}

	/**
	 * Injection des accords sur la grille
	 * 
	 * @param grilleAccord
	 */
	public void injectSeq(Grille grilleAccord) {
		try {
			this.sequencer = MidiSystem.getSequencer();
			this.sequencer.open();
			this.sequence = new Sequence(Sequence.PPQ, 4);
			this.tempo = grilleAccord.getTempo();

			// IMPORTANT ICI CREATION DE LA TRACK DES ACCORDS
			this.track = this.sequence.createTrack();

			this.sequencer.setSequence(this.sequence);

			this.sequencer.setTempoInBPM(this.tempo);
			// Long deplacementCurseur = 0f;
			long newPos = 0;
			for (Chord c : grilleAccord.chords) {
				List<Integer> valNoteToPlay = c.chordToValues();

				for (int valeur : valNoteToPlay) {
					if (valeur < 4)
						valeur += 24;
					if (valeur < 9)
						valeur += 12;
					if (valeur > 36)
						valeur -= 12;

					putANote(this.track, c.getPlayed(), valeur, this.tempo, 4l / c.getTime(), newPos, c.getTime());

				}
				newPos += 8 / c.getTime(); // nouvellePosition;
			}

			while (true) {
				// Exit the program when sequencer has stopped playing.
				if (!this.sequencer.isRunning()) {
					this.sequencer.stop();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("OOPPS: " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		this.sequencer.stop();
	}

	/**
	 * Methode pour créer une ligne de WalkingBasse réfléchie et esthétique. TODO
	 * Implémentation à reprendre
	 * 
	 * @param c
	 * @param commeLePrecedent
	 * @param accSuivant
	 * @return
	 */
	public String iA(Chord c, boolean commeLePrecedent, Chord accSuivant) {

		Random monRandom = new Random();
		monRandom.ints(0, 4);
		Integer valFondAccSuivant = Note.noteToVal(accSuivant.getFondamental().toString());
		Boolean commeLeSuivant = (c.toString()).contentEquals(accSuivant.toString());

		if (c.getTime() == 4 && commeLePrecedent && commeLeSuivant) {

			if (compte > 3) {
				int susu = compte;
				compte = 0;
				return accSuivant.chordToComponents().get(susu - 1);
			}
			if (compte == 7 % 3) {
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				compte = 0;
				System.out.println(Note.valToNote(valFondAccSuivant));
				return Note.valToNote(valFondAccSuivant); // on renvoi une note de passage un demiton en dessous
			}
			compte += 1;
			System.out.println(compte);
			return c.chordToComponents().get((c.chordToComponents().size() - 1) % (compte + monRandom.nextInt(4)));

		}
		return ((c.getBasse() == null || c.getBasse().toString() == "") ? c.getFondamental().toString()
				: c.getBasse().toString());
	}

	/**
	 * Injection de la ligne de basse selon la méthode iA(Chord c, boolean
	 * commeLePrecedent, Chord accSuivant)
	 * 
	 * @param grilleAccord
	 */
	public void injectBasse(Grille grilleAccord) {
		try {

			// // IMPORTANT ICI CREATION DE LA TRACK DE LA BASSE
			this.trackBasse = this.sequence.createTrack();

			this.sequencer.setSequence(this.sequence);

			this.sequencer.setTempoInBPM(this.tempo);

			long newPos = 0;
			String sosisse = "";
			List<?> list = Collections.synchronizedList(grilleAccord.chords);
			synchronized (list) {
				Iterator<?> iterator = list.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.toString());
					iterator.next();
				}
			}

			Integer cpter = 0;
			for (Chord c : grilleAccord.chords) {

				Chord accSuivant = grilleAccord.chords.get(cpter);
				Boolean commeLePrecedent = (c.toString()).contentEquals(sosisse);

				Integer valeur = Note.noteToVal(iA(c, commeLePrecedent, accSuivant));

				putANote(this.trackBasse, true, valeur - 13, this.tempo, 4l / c.getTime(), newPos, c.getTime());

				newPos += 8 / c.getTime(); // nouvellePosition;
				sosisse = c.toString();
				cpter += 1; // Chord accSuivant = c.next();
			}

			while (true) {
				// Exit the program when sequencer has stopped playing.
				if (!this.sequencer.isRunning()) {
					this.sequencer.stop();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("OOPPS: " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		// this.sequencer.stop();
	}

	/**
	 * Méthode qui joue la grille entiere TODO : Possibilité de la jouer plusieurs
	 * fois en rajoutant un parametre
	 * 
	 * @see this.sequencer.setLoopCount(1);
	 */
	public void play() {
		try {
			this.sequencer.setSequence(this.sequence);
			this.sequencer.setLoopCount(1);
			this.sequencer.start();
			while (true) {
				// Exit the program when sequencer has stopped playing.
				if (!this.sequencer.isRunning()) {
					this.sequencer.stop();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Stopper la séquence de la grille
	 */
	public void stop() {
		try {
			this.sequencer.stop();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
