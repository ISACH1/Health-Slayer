package ru.kirillisachenko.virusgame.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;

public class Room {
    private MathGenerator mathGenerator;
    private Tile tiles[][] = new Tile[100][100];
    private Bitmap tile1, tile2, tile3, tile4;

    public Room(float xPosition, float yPosition, Context context) {
        mathGenerator = new MathGenerator();
        tile1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile1), 40, 40, false);
        tile2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile2), 40, 40, false);
        tile3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile3), 40, 40, false);
        tile4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile4), 40, 40, false);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int tile = mathGenerator.getRandom(5, 0);
                if (tile == 1)
                    tiles[i][j] = new Tile(xPosition + j * 40, yPosition + i * 40, tile1);
                if (tile == 2)
                    tiles[i][j] = new Tile(xPosition + j * 40, yPosition + i * 40, tile2);
                if (tile == 3)
                    tiles[i][j] = new Tile(xPosition + j * 40, yPosition + i * 40, tile3);
                if (tile == 4)
                    tiles[i][j] = new Tile(xPosition + j * 40, yPosition + i * 40, tile4);
            }
        }
    }

    public float getXPoint(int i, int j) {
        return tiles[i][j].TileXPosition;
    }

    public float getYPoint(int i, int j) {
        return tiles[i][j].TileYPosition;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawColor(Color.BLACK);
    }


    class Tile {
        private float TileXPosition;
        private float TileYPosition;
        private Bitmap tile;

        public Tile(float xPosition, float yPosition, Bitmap tile) {
            this.TileXPosition = xPosition;
            this.TileYPosition = yPosition;
            this.tile = tile;
        }

        public void draw(Canvas canvas, GameDisplay gameDisplay) {
            canvas.drawBitmap(tile, gameDisplay.gameToDisplayCoordinatesX(TileXPosition), gameDisplay.gameToDisplayCoordinatesY(TileYPosition), null);
        }
    }
}
