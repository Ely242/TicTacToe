import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    final int boardWidth = 600, boardHeight = 650;
    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean GameOver = false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label.setBackground(Color.darkGray);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setText("Tic Tac Toe");

        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = new JButton();
                board[r][c].setFont(new Font("Arial", Font.BOLD, 120));
                board[r][c].setBackground(Color.white);
                board[r][c].setFocusable(false);
                board[r][c].setFocusPainted(false);
                board[r][c].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (GameOver)
                            return;
                        JButton button = (JButton) e.getSource();
                        if (button.getText().equals("")) {
                            button.setText(currentPlayer);
                            checkWin();
                            if (!GameOver) {
                                if (checkDraw()) {
                                    label.setText("It's a draw!");
                                    // show for 3 seconds then reset board
                                    Timer timer = new Timer(3000, new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            resetBoard();
                                            GameOver = false;
                                            currentPlayer = (currentPlayer.equals(playerX) ? playerO : playerX);
                                            label.setText(currentPlayer + "'s turn");
                                        }
                                    });
                                    timer.setRepeats(false);
                                    timer.start();
                                } else {
                                    currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                    label.setText(currentPlayer + "'s turn");
                                }
                            } else {
                                label.setText(currentPlayer + " wins!");
                                // show for 3 seconds then reset board
                                Timer timer = new Timer(3000, new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        resetBoard();
                                        GameOver = false;
                                        currentPlayer = (currentPlayer.equals(playerX) ? playerO : playerX);
                                        label.setText(currentPlayer + "'s turn");
                                    }
                                });
                                timer.setRepeats(false);
                                timer.start();
                            }
                        }
                    }
                });
                boardPanel.add(board[r][c]);
            }
        }

    }

    public void checkWin() {
        // check each row
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals(currentPlayer) && board[r][1].getText().equals(currentPlayer)
                    && board[r][2].getText().equals(currentPlayer)) {
                for (int i = 0; i < 3; i++) {
                    setWinner(r, i);
                }
                GameOver = true;
                return;
            }
        }
        // check each column
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals(currentPlayer) && board[1][c].getText().equals(currentPlayer)
                    && board[2][c].getText().equals(currentPlayer)) {
                for (int i = 0; i < 3; i++) {
                    setWinner(i, c);
                }
                GameOver = true;
                return;
            }
        }
        // check diagonals
        if (board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
                && board[2][2].getText().equals(currentPlayer)) {
            for (int i = 0; i < 3; i++) {
                setWinner(i, i);
            }
            GameOver = true;
            return;
        }
        if (board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
                && board[2][0].getText().equals(currentPlayer)) {
            setWinner(0, 2);
            setWinner(1, 1);
            setWinner(2, 0);
            GameOver = true;
        }
    }

    void setWinner(int r, int c) {
        board[r][c].setForeground(Color.green);
    }

    public boolean checkDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getText().equals("")) {
                    return false;
                }
            }
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setBackground(Color.red);
            }
        }
        return true;
    }

    public void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.white);
                board[r][c].setForeground(Color.black);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

}