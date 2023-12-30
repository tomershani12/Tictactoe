package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }

    private void handleClick(int row, int col, int id) {
        if (board[row][col].equals("")) {
            board[row][col] = turn;
            Button btn = findViewById(id);
            btn.setText(turn);
            onTurnEnd();
        }

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
            }
        }
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
    }
}