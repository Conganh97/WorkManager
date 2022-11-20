package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralInformation {
    public Date dateCreated;
    public String creator;
    public Date dateUpdated;
    public String editor;


    public GeneralInformation(Date dateCreated, String creator) {
        this.dateCreated = dateCreated;
        this.creator = creator;
    }

    public GeneralInformation() {
    }

    public String displayDateCreated (){
        if (dateCreated == null){
            return "";
        } else {
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            return df.format(dateCreated);
        }
    }

    public String displayDateEdited (){
        if (dateUpdated == null){
            return "";
        }else {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return df.format(dateUpdated);}
    }


    public String getCreator() {
        return creator;
    }


    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
