package blak.android.example.adapterseparated;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import blak.android.adapters.SeparatedListAdapter;
import blak.android.example.R;

import java.util.ArrayList;
import java.util.List;

public class SeparatedHeadersActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afs__listview);

        initList();
    }

    private void initList() {
        SeparatedListAdapter adapter = new SeparatedListAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);

        List<Friend> friends = createFriendsList();
        FriendsAdapter friendsAdapter = new FriendsAdapter(getApplicationContext(), friends);
        String friendsHeader = "Friends";
        adapter.addSection(friendsHeader, friendsAdapter);

        List<Invite> invites = createInvitesList();
        invitesAdapter invitesAdapter = new invitesAdapter(getApplicationContext(), invites);
        String invitesHeader = "Invites";
        adapter.addSection(invitesHeader, invitesAdapter);

        ListView list = (ListView) findViewById(R.id.afs__listview);
        list.setAdapter(adapter);
    }

    private List<Friend> createFriendsList() {
        List<Friend> friends = new ArrayList<Friend>();
        friends.add(new Friend("Neo"));
        friends.add(new Friend("Trinity"));
        friends.add(new Friend("Morpheus"));
        return friends;
    }

    private List<Invite> createInvitesList() {
        List<Invite> invites = new ArrayList<Invite>();
        invites.add(new Invite("Bob", null));
        invites.add(new Invite(null, "+456456"));
        invites.add(new Invite(null, "asd@asd.asd"));
        invites.add(new Invite("Alice", null));
        return invites;
    }
}
