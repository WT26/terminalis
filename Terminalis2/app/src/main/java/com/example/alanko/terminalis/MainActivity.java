package com.example.alanko.terminalis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button nappi;
    EditText cmd;
    TextView terminal;
    String terminal_txt = ">";
    String version = "Terminalis 1.1.11a";
    ListView lista;


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

    protected String komenna( String terminal_txt ) {

        String command = cmd.getText().toString();
        if (command.equals("commands") || command.equals("cmds")) {
            terminal_txt = terminal_txt + "commands: commands, clr, ver, info, sp, google\n>";
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


            else {
                terminal_txt = terminal_txt + "wrong cmd, to see all 'commands'\n>";
                terminal.setText(terminal_txt);
                return terminal_txt;
            }
        } catch (Exception ArrayIndexOutOfBoundsException){
            terminal_txt = terminal_txt + "use google:search_this\n>";
            terminal.setText(terminal_txt);
            return terminal_txt;}
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
