package com.edwinhollen.doremi2

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.Viewport
import com.edwinhollen.doremi2.music.Chromatic
import com.edwinhollen.doremi2.music.Note
import com.edwinhollen.doremi2.puzzle.EasyFourNotePuzzle
import com.edwinhollen.doremi2.puzzle.FourNotePuzzle
import java.util.logging.Logger

/**
 * Created by Edwin on 1/15/2017.
 */
class GameStage : Stage {
    val atlas: TextureAtlas;

    constructor(viewport: Viewport?, batch: Batch?) : super(viewport, batch){
        this.setDebugAll(true);

        val puzzle: FourNotePuzzle = EasyFourNotePuzzle();
        this.atlas = TextureAtlas(Gdx.files.internal("packed.atlas"));

        puzzle.solutionNotes.forEachIndexed { i, note ->
            val newNoteActor: NotePiece = when(note){
                puzzle.solutionNotes.first() -> MaleNotePiece(note);
                puzzle.solutionNotes.last() -> FemaleNotePiece(note);
                else -> MiddleNotePiece(note);
            }
            addActor(newNoteActor);
        }


    }

    inner open class NotePiece(note: Note, left: Image, right: Image) : Group() {
        init {
            val mid: Image = Image(this@GameStage.atlas.findRegion("middle"));
            this.addActor(mid);
            mid.width = Math.round(viewport.worldWidth * 0.065f).toFloat();

            this.setSize(mid.width, mid.height);

            this.addActor(left);
            this.addActor(right);

            val noteHead: Image = Image(this@GameStage.atlas.findRegion("notehead"));
            // there's probably a better way to do this
            noteHead.x = this.width * 0.5f;
            noteHead.y = (when(note.chromatic){
                Chromatic.C_NATURAL, Chromatic.C_SHARP -> when(note.octave){
                    0 -> 0.19f;
                    else -> 0.65f;
                }
                Chromatic.D_NATURAL -> when(note.octave){
                    0 -> 0.23f;
                    else -> 0.72f;
                }
                Chromatic.E_FLAT, Chromatic.E_NATURAL -> when(note.octave){
                    0 -> 0.30f;
                    else -> 0.79f;
                }
                Chromatic.F_NATURAL, Chromatic.F_SHARP -> when(note.octave){
                    0 -> 0.37f;
                    else -> 0.86f;
                }
                Chromatic.G_NATURAL -> when(note.octave){
                    0 -> 0.44f;
                    else -> 0.93f;
                }
                Chromatic.A_FLAT, Chromatic.A_NATURAL -> when(note.octave){
                    0 -> 0.51f;
                    else -> 0.98f;
                }
                Chromatic.B_FLAT, Chromatic.B_NATURAL -> when(note.octave){
                    else -> 0.58f;
                }
            }) * this.height;

            this.addActor(noteHead);

            this.addListener(object : ActorGestureListener(){
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                    super.touchDown(event, x, y, pointer, button)
                    this@NotePiece.toFront();
                }

                override fun pan(event: InputEvent?, x: Float, y: Float, deltaX: Float, deltaY: Float) {
                    super.pan(event, x, y, deltaX, deltaY);
                    this@NotePiece.moveBy(Math.round(deltaX).toFloat(), Math.round(deltaY).toFloat());
                }
            });

            left.setPosition(0f, 0f, Align.bottomRight);
            right.setPosition(this.width, 0f, Align.bottomLeft);
        }
    }

    inner class MaleNotePiece(note: Note) : NotePiece(note, Image(this@GameStage.atlas.findRegion("flat_left")), Image(this@GameStage.atlas.findRegion("male"))) {

    }

    inner class FemaleNotePiece(note: Note) : NotePiece(note, Image(this@GameStage.atlas.findRegion("female")), Image(this@GameStage.atlas.findRegion("flat_right"))){

    }

    inner class MiddleNotePiece(note: Note) : NotePiece(note, Image(this@GameStage.atlas.findRegion("female")), Image(this@GameStage.atlas.findRegion("male"))){

    }

}