package Model;

public class Puzzle extends GameObject{
    protected String answer;
    protected String hint;
    protected int numAttempts;
    protected boolean solved;


    public Puzzle(String objectId, String name, String description, int roomLocation, String answer, String hint, int numAttempts, boolean solved) {
        super(objectId, name, description, roomLocation);
        this.answer = answer;
        this.hint = hint;
        this.numAttempts = numAttempts;
        this.solved = solved;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getNumAttempts() {
        return numAttempts;
    }

    public void setNumAttempts(int numAttempts) {
        this.numAttempts = numAttempts;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
