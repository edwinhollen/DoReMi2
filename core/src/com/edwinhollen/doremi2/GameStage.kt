package com.edwinhollen.doremi2

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.Viewport
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
        val puzzle: FourNotePuzzle = EasyFourNotePuzzle();

        this.atlas = TextureAtlas(Gdx.files.internal("packed.atlas"));
    }

    inner open class NotePiece : Group{
        constructor(note: Note) : super(){
            this.userObject = note;
            val mid = Image(this@GameStage.atlas.findRegion("middle"));
            mid.width = mid.width * 1.5f;
        }
    }

    inner class MaleNotePiece(note: Note) : NotePiece(note) {
        init{

        }
    }

    inner class FemaleNotePiece(note: Note) : NotePiece(note){
        init{

        }
    }

    inner class MiddleNotePiece(note: Note) : NotePiece(note){
        init{

        }
    }

}