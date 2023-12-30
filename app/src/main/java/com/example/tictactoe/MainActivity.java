package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.ToneGenerator;

public class MainActivity extends AppCompatActivity {

    ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
    private static final int TONE_X_MOVE = ToneGenerator.TONE_CDMA_CONFIRM;
    private static final int TONE_O_MOVE = ToneGenerator.TONE_PROP_ACK;

    String turn;
    String[][] board;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onNewGame();
    }

    public void onButtonClick(View view) {

        int viewID = view.getId();

        if (viewID == R.id.btn_00) {
            handleClick(0, 0, R.id.btn_00);
        } else if (viewID == R.id.btn_01) {
            handleClick(0, 1, R.id.btn_01);
        } else if (viewID == R.id.btn_02) {
            handleClick(0, 2, R.id.btn_02);
        } else if (viewID == R.id.btn_10) {
            handleClick(1, 0, R.id.btn_10);
        } else if (viewID == R.id.btn_11) {
            handleClick(1, 1, R.id.btn_11);
        } else if (viewID == R.id.btn_12) {
            handleClick(1, 2, R.id.btn_12);
        } else if (viewID == R.id.btn_20) {
            handleClick(2, 0, R.id.btn_20);
        } else if (viewID == R.id.btn_21) {
            handleClick(2, 1, R.id.btn_21);
        } else if (viewID == R.id.btn_22) {
            handleClick(2, 2, R.id.btn_22);
        }
    }

    private void onNewGame() {
        board = new String[3][3];
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = new String();

        turn = "X";
        count = 0;
        clearButton(R.id.btn_00);
        clearButton(R.id.btn_01);
        clearButton(R.id.btn_02);
        clearButton(R.id.btn_10);
        clearButton(R.id.btn_11);
        clearButton(R.id.btn_12);
        clearButton(R.id.btn_20);
        clearButton(R.id.btn_21);
        clearButton(R.id.btn_22);
    }

    private void handleClick(int row, int col, int id) {
        if (board[row][col].equals("")) {
            board[row][col] = turn;
            Button btn = findViewById(id);
            btn.setText(turn);
            onTurnEnd();
        }

    }
    private void clearButton( int id) {
            Button btn = findViewById(id);
            btn.setText(null);
    }
    private void onTurnEnd() {
        if (isWinner())
            endGame(turn + " won!");
        else {
            count++;
            if (count == 9)
                endGame("Tie");
            else {
                turn = (turn.equals("X") ? "O" : "X");
                TextView txtTurn = findViewById(R.id.txtTurn);
                txtTurn.setText("Turn of: "+turn);
            }
        }
        if (turn.equals("X"))        //Play tone
            toneGenerator.startTone(TONE_X_MOVE);
        else
            toneGenerator.startTone(TONE_O_MOVE);

    }
    private boolean isWinner() {
        String winner = "None";

        // Check for a winner in rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals("X") && board[i][1].equals("X") && board[i][2].equals("X") ||
                    board[0][i].equals("X") && board[1][i].equals("X") && board[2][i].equals("X")) {
                winner = "X";
            } else if (board[i][0].equals("O") && board[i][1].equals("O") && board[i][2].equals("O") ||
                    board[0][i].equals("O") && board[1][i].equals("O") && board[2][i].equals("O")) {
                winner = "O";
            }
        }

        // Check for a winner in diagonals
        if (board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X") ||
                board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X")) {
            winner = "X";
        } else if (board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O") ||
                board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O")) {
            winner = "O";
        }
        if (winner.equals("None"))
            return false;
        else
            return true;
    }
    private  void endGame(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Tic-Tac-Toe")
                .setMessage("Game ended with: "+ message);

        builder.setPositiveButton("Start New Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                onNewGame();
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}