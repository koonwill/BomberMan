public abstract class Command {
    private Player player;

    public Command(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void execute();
}
