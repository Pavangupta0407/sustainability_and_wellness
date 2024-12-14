public class userprofile extends AppCompatActivity {
	TextInputLayout fullname,email,phoneno,password;
	TextView fullnamelabel,usernamelabel;
	@Override
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userprofile);
		fullname=findViewById(R.id.full_name_profile);
		email=findViewById(R.id.email_profile);
		phoneno=findViewById(R.id.phoneno_profile);
		password=findViewById(R.id.password_profile);
		fullnamelabel=findViewById(R.id.fullname_field);
		usernamelabel=findViewById(R.id.username_field);

		showAllUserData();
	}
	private void showAllUserData(){
		Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
		usernameLabel.setText(user_username);
		fullName.getEditText().setText(user_name);
		email.getEditText().setText(user_email);
		phoneNo.getEditText().setText(user_phoneNo);
		password.getEditText().setText(user_password);
	}
}