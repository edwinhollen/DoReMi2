package com.edwinhollen.doremi2

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import kotlin.reflect.KClass

/**
 * Created by Edwin on 1/15/2017.
 */
class Game() : ApplicationAdapter() {

    override fun create() {
        batch = SpriteBatch();
        viewport = FitViewport(800f, 600f);

        changeStage(::GameStage);
    }

    override fun render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage?.act();
        stage?.draw();
    }

    override fun resize(width: Int, height: Int) {
        viewport?.update(width, height, true);
    }

    override fun dispose() {
        batch?.dispose();
        stage?.dispose();
    }

    companion object{
        var stage: Stage? = null;
        var viewport: Viewport? = null;
        var batch: Batch? = null;
        fun changeStage(newStageClass: (Viewport, Batch) -> Stage){
            this.stage?.dispose();
            this.stage = newStageClass(this.viewport as Viewport, this.batch as Batch)
            Gdx.input.inputProcessor = this.stage;
        }
    }
}