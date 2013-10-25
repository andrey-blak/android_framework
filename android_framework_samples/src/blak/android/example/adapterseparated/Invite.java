package blak.android.example.adapterseparated;

public class Invite {
    public String mName;
    public String mContact;

    public Invite() {
    }

    public Invite(String name, String contact) {
        mName = name;
        mContact = contact;
    }

    public String getContact() {
        return mContact;
    }

    public String getName() {
        return mName;
    }
}
