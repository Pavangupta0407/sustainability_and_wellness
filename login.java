public class login extends AppCompatActivity{
	Button callsignup,login_btn;
	ImageView image;
	TextView logoText,slogantext;
	TextInputLayout username,password;
	@Override
	protected void onCreate (){

	}
	private Boolean validateUsername(){
		String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
        username.setError("Field cannot be empty");
        return false;
    } else {
        username.setError(null);
        username.setErrorEnabled(false);
        return true;
    }

	}
	private Boolean validatePassword(){
		String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
        password.setError("Field cannot be empty");
        return false;
    } else {
        password.setError(null);
        password.setErrorEnabled(false);
        return true;
    }

	}
	public void loginUser(View view){
		//Validate Login Info 
        if (!validateUsername() | !validatePassword()) {
        return;
    } else {
        isUser();
    }

	}
	private void isUser() {
        progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String tempFromDB = dataSnapshot.child(userEnteredUsername).child("temp").getValue(String.class);
                        String ecgFromDB = dataSnapshot.child(userEnteredUsername).child("ecg").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("temp", phoneNoFromDB);
                        intent.putExtra("ecg", passwordFromDB);
                        startActivity(intent);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }
        });
    }

	public void callSignUpScreen(View view){

	}
}