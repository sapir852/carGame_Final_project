import java.util.ArrayList;

public class DataBase {

    private ArrayList<Save> save;

    public DataBase() { };

    public ArrayList<Save> getRecords() {
        if(save == null){
            save = new ArrayList<Save>();
        }
        return save;
    }

    public DataBase setSave(ArrayList<Save> records) {
        this.save = save;
        return this;
    }
}
