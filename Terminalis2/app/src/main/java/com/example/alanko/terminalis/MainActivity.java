package com.example.alanko.terminalis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button nappi;
    EditText cmd;
    TextView terminal;
    String terminal_txt = ">";
    String version = "Terminalis 1.1.17a";
    int iivi = 26;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//#########################################################################################

        nappi = (Button) findViewById(R.id.button);
        cmd = (EditText) findViewById(R.id.editText);
        terminal = (TextView) findViewById(R.id.textView);
        terminal.setMovementMethod(new ScrollingMovementMethod());
        cmd.setFocusableInTouchMode(true);
        cmd.requestFocus();
        cmd.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    terminal_txt = komenna(terminal_txt);
                    cmd.setText("");
                    return true;
                }
                return false;
            }
        });

        nappi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                terminal_txt = komenna(terminal_txt);
                cmd.setText("");
            }
        });
    }

    protected int generate_seed(int iivi, int event_number, int counter, int twenty_six[]){
        if (counter >= 26) {
            counter = 0;
        }
        int iivi_number = iivi;
        // 1. First we add one number from 26list to the iivinumber
        iivi_number += (twenty_six[counter]);
        // 2. Then we add the number of the current event to iivinumber
        iivi_number = iivi_number + event_number;
        // 3. If iivi number is at this state over one hundread it minuses 100
        // from number. If its exactly 100 it goes back to 26.

        if (iivi_number > 100){
            iivi_number -= 100;
        }

        else if ( iivi_number == 100) {
            iivi_number = 26;
        }
        return iivi_number;
    }
    protected int ing(int event){

        int twentysix_lines[] = {8, 12, 22, 1, 20, 16, 26, 17, 5, 7, 18, 14,
                11, 9, 24, 3, 2, 15, 4, 21, 19, 6, 23, 13, 25, 10};
        iivi = generate_seed(iivi, event, counter, twentysix_lines);
        counter += 1;
        return iivi;

    }

    protected String komenna( String terminal_txt ) {

        String command = cmd.getText().toString();
        if (command.equals("commands") || command.equals("cmds")) {
            terminal_txt = terminal_txt + "commands: commands, clr, ver, info, sp, google, ing, wn, write, ksp,\n>randomcolor";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("clr")) {
            terminal_txt = ">";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("ver")) {
            terminal_txt = terminal_txt + "version: " + version + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("info")) {
            terminal_txt = terminal_txt + "terminalis is for personal use made terminal," +
                    "\n>where you can terminate few commands.\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("sp")) {
            terminal_txt = terminal_txt + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("wn")) {
            Calendar calendar = Calendar.getInstance();
            int week_number = calendar.get(Calendar.WEEK_OF_YEAR);
            terminal_txt = terminal_txt + "current week number is " + week_number + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("randomcolor")||command.equals("randcol")) {
            Random rand = new Random();
            int myRandomNumber = rand.nextInt(0x100000) + 0x100000; // Generates a random number between 0x10 and 0x20
            String result = Integer.toHexString(myRandomNumber); // Random hex number in result

            terminal_txt = terminal_txt + "random color: #" + result + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("")) {
            return terminal_txt;
        }


        try {
            String[] commands = command.split(":");
            if (commands[0].equals("google")||commands[0].equals("g")) {
                terminal_txt = terminal_txt + "opening google search..\n>";
                terminal.setText(terminal_txt);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/#q=" + commands[1]));
                startActivity(browserIntent);
                terminal_txt = terminal_txt + "search complete.\n>";
                terminal.setText(terminal_txt);
                return terminal_txt;
            }


            if (commands[0].equals("ing")) {
                int event = Integer.parseInt(commands[1]);
                int iivi_ = ing( event );
                terminal_txt = terminal_txt + "for event: " + event + "\n>iivi number is: " + iivi_ + "\n>";
                terminal.setText(terminal_txt);
                return terminal_txt;
            }


            if (commands[0].equals("write")||commands[0].equals("wr")) {
                String write = commands[1];
                terminal_txt = terminal_txt + write + "\n>";
                terminal.setText(terminal_txt);
                return terminal_txt;
            }


            if (commands[0].equals("ksp")) {
                String[] kivipaperisakset = {"k", "p", "s"};

                String opponent = "kivi";
                Random r = new Random();
                int i1 = r.nextInt(3);
                if (i1 == 0) {
                    opponent = "kivi";
                } else if (i1 == 1) {
                    opponent = "paperi";
                } else{
                    opponent = "sakset";
                }

                if(commands[1].equals("k")){
                    String valinta = "kivi";
                    if (kivipaperisakset[i1].equals(commands[1])){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> its draw\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("s") && commands[1].equals("p")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("k") && commands[1].equals("s")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent  + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("p") && commands[1].equals("k")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("s") && commands[1].equals("k")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("k") && commands[1].equals("p")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("p") && commands[1].equals("s")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                }
                else if(commands[1].equals("p")){
                    String valinta = "paperi";
                    if (kivipaperisakset[i1].equals(commands[1])){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> its draw\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("s") && commands[1].equals("p")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("k") && commands[1].equals("s")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("p") && commands[1].equals("k")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("s") && commands[1].equals("k")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("k") && commands[1].equals("p")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("p") && commands[1].equals("s")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                }
                else if(commands[1].equals("s")){
                    String valinta = "sakset";
                    if (kivipaperisakset[i1].equals(commands[1])){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> its draw\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("s") && commands[1].equals("p")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("k") && commands[1].equals("s")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("p") && commands[1].equals("k")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you lost\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("s") && commands[1].equals("k")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("k") && commands[1].equals("p")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                    else if (kivipaperisakset[i1].equals("p") && commands[1].equals("s")){
                        terminal_txt = terminal_txt + "your choice: " + valinta + "\n>opponents choice: " + opponent + "\n> you won\n>";
                        terminal.setText(terminal_txt);
                        return terminal_txt;
                    }
                }
                return terminal_txt;
            }


            else {
                terminal_txt = terminal_txt + "wrong cmd, to see all 'commands'\n>";
                terminal.setText(terminal_txt);
                return terminal_txt;
            }
        } catch (Exception ArrayIndexOutOfBoundsException){
            terminal_txt = terminal_txt + "command uses ':' to add parameters\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }
    }
//##########################################################################################
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
