package com.example.alanko.terminalis;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.os.Build;

import java.util.Calendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    Button nappi;
    Button x_nappi;
    EditText cmd;
    TextView terminal;
    String terminal_txt = ">";
    String version = "Terminalis 1.1.33a";
    int iivi = 26;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//#########################################################################################

        nappi = (Button) findViewById(R.id.button);
        x_nappi = (Button) findViewById(R.id.button2);
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

        x_nappi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                terminal.setText(">");
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
        if (command.equals("commands") || command.equals("cmds") || command.equals("c")) {
            terminal_txt = terminal_txt + "commands:\n>1. commands\n>2. info\n>3. ver\n>4. clr\n" +
                    ">5. sp\n>6. wn\n>7. randomcolor\n>8. google:search_this\n>9. write:print_this\n" +
                    ">10. ksp:your_choice\n>11. ing:event_number\n>12. sdk\n>13. device \n>14. brand\n" +
                    ">15. notify:notify_this\n>16. randomint\n>17. binary\n" +
                    ">";
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


        else if (command.equals("randomint")||command.equals("randint")) {
            Random rand = new Random();
            int myRandomNumber = rand.nextInt(10); // Generates a random number between 0x10 and 0x20

            terminal_txt = terminal_txt + "random integer: " + myRandomNumber + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("sdk")) {
            String sdk_text = android.os.Build.MODEL;
            terminal_txt = terminal_txt + "Your phone's sdk is: " + sdk_text + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("device")) {
            String device_text = android.os.Build.DEVICE;
            terminal_txt = terminal_txt + "Your phone's device is: " + device_text + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("brand")) {
            String brand_text = Build.BRAND;
            terminal_txt = terminal_txt + "Your phone's brand is: " + brand_text + "\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;
        }


        else if (command.equals("binary")) {

            //Basic binary here, nothing to see.. I still need a littlebit more. 26
            terminal_txt = terminal_txt + "some binary for you:\n>" +
                    "0100001001100001011100110110100101100011\n>" +
                    "00100000011000100110100101101110011000010\n>" +
                    "11100100111100100100000011010000110010101\n>" +
                    "11001001100101001011000010000001101110011\n>" +
                    "01111011101000110100001101001011011100110\n>" +
                    "01110010000001110100011011110010000001110\n>" +
                    "01101100101011001010010111000101110001000\n>" +
                    "00010010010010000001110011011101000110100\n>" +
                    "10110110001101100001000000110111001100101\n>" +
                    "01100101011001000010000001100001001000000\n>" +
                    "11011000110100101110100011101000110110001\n>" +
                    "10010101100010011010010111010000100000011\n>" +
                    "01101011011110111001001100101001011100010\n>" +
                    "00000011001000110110001100101001011100010\n>";
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

            if (commands[0].equals("notify")) {
                String write = commands[1];

                Intent intent = new Intent(this, NotificationReceiver.class);
                PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

                Notification n  = new Notification.Builder(this)
                        .setContentTitle("Terminalis notification")
                        .setContentText(write)
                        .setSmallIcon(R.drawable.buttonshape)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, n);

                terminal_txt = terminal_txt + "Notification done.\n>";
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
