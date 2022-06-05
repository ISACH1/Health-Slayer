package ru.kirillisachenko.virusgame.Utils.DataStorages;




import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.kirillisachenko.virusgame.Activities.Adapters_and_Controllers.AuthController;


public class RealTimeDataBase {
    private AuthController authController;
    private FirebaseDatabase storage;
    private DatabaseReference storageRef;
    private String email;
    public RealTimeDataBase(){
        storage = FirebaseDatabase.getInstance();
        storageRef = storage.getReference("USERS");
        authController = new AuthController();
        email = authController.getUser().getEmail().replace('.', ' ');
    }


    public void writeScore(int score){
        storageRef.child(email).setValue(score);
    }

    public void getScore(OnCompleteListener<DataSnapshot> listener){
        storageRef.child(email).get().addOnCompleteListener(listener);
    }
}
