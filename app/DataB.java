import java.util.ArrayList;

public class DataB {

    private ArrayList<Save> save;

    public DataB() { };

    public ArrayList<Save> getRecords() {
        if(save == null){
            save = new ArrayList<Save>();
        }
        return save;
    }

    public DataB setSave(ArrayList<Save> records) {
        this.save = save;
        return this;
    }
}
