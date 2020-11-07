package com.example.numazu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String type;
    private String type_locale;
    private String location;
    private String location_locale;
    private ListData[] listData;
    private boolean switchState;
    private Random generator;

    private Locale loc;
    private String filename;
    private String positiveButton;
    private String negativeButton;
    private String hours_line_extra;
    private String rest_line_extra;
    private String day_line_extra;
    private String[] days_of_week;
    private String closed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        generator = new Random();
        loc = Locale.getDefault();
        this.type = DataHolder.getType();
        this.type_locale = DataHolder.getTypeLocale();
        this.location = DataHolder.getLocation();
        this.location_locale = DataHolder.getLocationLocale();
        adjustLocale();
        String tView_text;
        if (loc.getLanguage().equals(new Locale("ko").getLanguage()) || loc.getLanguage().equals(new Locale("jp").getLanguage())) {
            tView_text = type_locale + " | " + location_locale;
        } else {
            tView_text = type_locale.toUpperCase() + " | " + location_locale.toUpperCase();
        }
        TextView tView = findViewById(R.id.textView3);
        tView.setText(tView_text);
        recyclerView = findViewById(R.id.rlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Switch switch1 = findViewById(R.id.switch1);
        switchState = switch1.isChecked();
        formList();
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchState = isChecked;
                formList();
            }
        });
        Button rand = findViewById(R.id.button15);
        rand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (listData.length != 0) {
                    int randomIndex = generator.nextInt(listData.length);
                    String name = listData[randomIndex].getName();
                    String type = listData[randomIndex].getType();
                    String menu = listData[randomIndex].getMenu();
                    String address_jp = listData[randomIndex].getAddress_jp();
                    String address = listData[randomIndex].getAddress();
                    String day = listData[randomIndex].getDay();
                    int oHour = listData[randomIndex].getOhour();
                    int oMinute = listData[randomIndex].getOminute();
                    int cHour = listData[randomIndex].getChour();
                    int cMinute = listData[randomIndex].getCminute();
                    int rsHour = listData[randomIndex].getRshour();
                    int rsMinute = listData[randomIndex].getRsminute();
                    int reHour = listData[randomIndex].getRehour();
                    int reMinute = listData[randomIndex].getReminute();
                    String longitude = listData[randomIndex].getLongitude();
                    String latitude = listData[randomIndex].getLatitude();
                    showDescription(name, type, menu, address_jp, address, day, oHour, oMinute, cHour, cMinute, rsHour, rsMinute, reHour, reMinute, longitude, latitude);
                } else {
                    int randomIndex = 0;
                }
            }
        });
    }

    public void showDescription(String name, String type, String menu, String address_jp, String address, String day, int oHour, int oMinute, int cHour, int cMinute, int rsHour, int rsMinute, int reHour, int reMinute, String longitude, String latitude) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String type_line;
        String menu_line;
        String address_line;
        String hours_line;
        String rest_line = "";
        String day_line = "";
        type_line = type + "\n";
        menu_line = menu + "\n";
        address_line = address + "\n";
        hours_line = hours_line_extra + ": " + oHour + ":" + oMinute + " - " + cHour + ":" + cMinute + "\n";
        if (oHour == -1 && oMinute == -1 && cHour == -1 && cMinute == -1) {
            hours_line = closed;
        }
        if (!(rsHour == -1 || rsMinute == -1 || reHour == -1 || reMinute == -1)) {
            rest_line = rest_line_extra + ": " + rsHour + ":" + rsMinute + " - " + reHour + ":" + reMinute + "\n";
        }

        if (!day.isEmpty()) {
            day_line = day_line_extra + ": ";
        }

        for (int i = 0; i < day.length(); i++) {
            char c = day.charAt(i);
            int ci = c - 65;
            day_line = day_line + days_of_week[ci];
            if (!(i + 1 < day.length())) {
                day_line = day_line + "\n";
            } else {
                day_line = day_line + ", ";
            }
        }

        String dialogMessage = menu_line + address_line + hours_line + rest_line + day_line;
        if (this.type == "numazu stamp rally") {
            dialogMessage = type_line + menu_line + address_line + hours_line + rest_line + day_line;
        } else {
            dialogMessage = menu_line + address_line + hours_line + rest_line + day_line;
        }

        builder.setMessage(dialogMessage)
                .setTitle(name);

        String uriStr = "geo:" + latitude + "," + longitude + "?q=" + address_jp;
        final Uri geoUri = Uri.parse(uriStr);

        builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        builder.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void adjustLocale() {
        String lang = loc.getLanguage();
        if (this.type.equals("numazu stamp rally")) {

            if (lang.equals(new Locale("ko").getLanguage())) {
                filename = "numazu_stamp_ko.csv";
                positiveButton = "지도에서 보기";
                negativeButton = "닫기";
                hours_line_extra = "영업시간";
                rest_line_extra = "휴식시간";
                day_line_extra = "휴일";
                days_of_week = new String[]{"월", "화", "수", "목", "금", "토", "일"};
                closed = "페업";
            } else if (lang.equals(new Locale("jp").getLanguage())) {
                filename = "numazu_stamp_jp.csv";
                positiveButton = "マップ見る";
                negativeButton = "とじる";
                hours_line_extra = "営業時間";
                rest_line_extra = "休憩時間";
                day_line_extra = "休日";
                days_of_week = new String[]{"月", "火", "水", "木", "金", "土", "日"};
                closed = "廃業";
            } else {
                filename = "numazu_stamp_en.csv";
                positiveButton = "Open in Maps";
                negativeButton = "Close";
                hours_line_extra = "Business Hours";
                rest_line_extra = "Intermission";
                day_line_extra = "Holiday";
                days_of_week = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                closed = "Out of Business";
            }
        } else {
            if (lang.equals(new Locale("ko").getLanguage())) {
                filename = "numazu_ko.csv";
                positiveButton = "지도에서 보기";
                negativeButton = "닫기";
                hours_line_extra = "영업시간";
                rest_line_extra = "휴식시간";
                day_line_extra = "휴일";
                days_of_week = new String[]{"월", "화", "수", "목", "금", "토", "일"};
            } else if (lang.equals(new Locale("jp").getLanguage())) {
                filename = "numazu_jp.csv";
                positiveButton = "マップ見る";
                negativeButton = "とじる";
                hours_line_extra = "営業時間";
                rest_line_extra = "休憩時間";
                day_line_extra = "休日";
                days_of_week = new String[]{"月", "火", "水", "木", "金", "土", "日"};
            } else {
                filename = "numazu_en.csv";
                positiveButton = "Open in Maps";
                negativeButton = "Close";
                hours_line_extra = "Business Hours";
                rest_line_extra = "Intermission";
                day_line_extra = "Holiday";
                days_of_week = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            }
        }
    }

    private void formList() {
        Calendar cal = Calendar.getInstance();
        int count = 0;
        String mLine;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(filename), "UTF-8"));

            while ((mLine = reader.readLine()) != null) {
                if (!mLine.isEmpty()) {
                    String[] data = mLine.split(",");
                    if (data[1].equals(this.type) || this.type.equals("all") || this.type.equals("numazu stamp rally")) {
                        if (data[2].equals(this.location) || this.location.equals("all")) {
                            if (!switchState || compareTime(data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14])) {
                                count++;
                            }
                        }
                    }
                }
            }
        } catch (IOException ignored) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }

        reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(filename), "UTF-8"));

            listData = new ListData[count];
            int pos = 0;
            while ((mLine = reader.readLine()) != null) {
                // added mline isempty.
                if (!mLine.isEmpty()) {
                    String[] data = mLine.split(",");
                    if (data[1].equals(this.type) || this.type.equals("all") || this.type.equals("numazu stamp rally")) {
                        if (data[2].equals(this.location) || this.location.equals("all")) {
                            if (!switchState || compareTime(data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14])) {
                                listData[pos] = new ListData(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14], data[15], data[16]);
                                pos++;
                            }
                        }
                    }
                }
            }

        } catch (IOException ignored) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }

        mAdapter = new ListAdapter(this, listData);
        recyclerView.setAdapter(mAdapter);
    }

    private boolean compareTime(String od, String oh, String om, String ch, String cm, String rsh, String rsm, String reh, String rem) {
        Calendar cal = Calendar.getInstance();
        Calendar oc = Calendar.getInstance();
        oc.set(Calendar.HOUR_OF_DAY, Integer.parseInt(oh));
        oc.set(Calendar.MINUTE, Integer.parseInt(om));
        Calendar cc = Calendar.getInstance();
        cc.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ch));
        cc.set(Calendar.MINUTE, Integer.parseInt(cm));
        boolean restingHours = true;
        Calendar rsc = Calendar.getInstance();
        Calendar rec = Calendar.getInstance();
        if (rsh.equals("-1") || rsm.equals("-1") || reh.equals("-1") || rem.equals("-1")) {
            restingHours = false;
        } else {
            rsc.set(Calendar.HOUR_OF_DAY, Integer.parseInt(rsh));
            rsc.set(Calendar.MINUTE, Integer.parseInt(rsm));
            rec.set(Calendar.HOUR_OF_DAY, Integer.parseInt(reh));
            rec.set(Calendar.MINUTE, Integer.parseInt(rem));
        }
        String d;
        if (cc.before(oc)) {
            cc.set(Calendar.DATE, cc.get(Calendar.DATE) + 1);
            int id = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (id == -1) {
                id = 6;
            }
            d = "GABCDEF".substring(id, id + 1);
        } else {
            int id = cal.get(Calendar.DAY_OF_WEEK);
            d = "GABCDEF".substring(id - 1, id);
        }

        if (od.contains(d)) {
            return false;
        }

        if (rec.before(rsc) && restingHours) {
            rec.set(Calendar.DATE, rec.get(Calendar.DATE) + 1);
        }

        if (oc.compareTo(cal) * cal.compareTo(cc) > 0) {
            if (restingHours && rsc.compareTo(cal) * cal.compareTo(rec) <= 0) {
                return true;
            } else return !restingHours;
        }

        return false;
    }
}
