package kr.codesqaud.chessgame.chess;

public class ConsoleView implements View {

    @Override
    public void showMessage(final String message) {
        System.out.println(message);
    }

    @Override
    public void showPrompt() {
        System.out.print("> ");
    }
}
