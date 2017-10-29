package nl.tue.demothermostat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;
import org.thermostatapp.util.WeekProgram;

public class WeekOverview extends Activity {

    WeekProgram wpg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_overview);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Get the week program
                    wpg = HeatingSystem.getWeekProgram();


                    // get used switches and output in new tablerow
                    for (Switch swtch : wpg.data.get("Monday")) {
                            if (swtch.getState()){
                                // setting styling of the rows and text
                                final TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                                final TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                                final TableLayout tbl=(TableLayout) findViewById(R.id.tablemon);
                                //Creating new tablerows and textviews
                                TableRow row= new TableRow(WeekOverview.this);
                                TextView txt1= new TextView(WeekOverview.this);
                                TextView txt2= new TextView(WeekOverview.this);
                                //setting the text
                                txt1.setText(swtch.getType());
                                txt2.setText(swtch.getTime());
                                txt1.setLayoutParams(params1);
                                txt2.setLayoutParams(params1);
                                //the textviews have to be added to the row created
                                row.addView(txt1);
                                row.addView(txt2);
                                row.setLayoutParams(params2);
                                tbl.addView(row);
                            }
                    }

                    for (Switch swtch : wpg.data.get("Tuesday")) {
                        if (swtch.getState()){
                            // setting styling of the rows and text
                            final TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                            final TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                            final TableLayout tbl=(TableLayout) findViewById(R.id.tabletue);
                            //Creating new tablerows and textviews
                            TableRow row= new TableRow(WeekOverview.this);
                            TextView txt1= new TextView(WeekOverview.this);
                            TextView txt2= new TextView(WeekOverview.this);
                            //setting the text
                            txt1.setText(swtch.getType());
                            txt2.setText(swtch.getTime());
                            txt1.setLayoutParams(params1);
                            txt2.setLayoutParams(params1);
                            //the textviews have to be added to the row created
                            row.addView(txt1);
                            row.addView(txt2);
                            row.setLayoutParams(params2);
                            tbl.addView(row);
                        }
                    }

                    for (Switch swtch : wpg.data.get("Wednesday")) {
                        if (swtch.getState()){
                            // setting styling of the rows and text
                            final TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                            final TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                            final TableLayout tbl=(TableLayout) findViewById(R.id.tablewed);
                            //Creating new tablerows and textviews
                            TableRow row= new TableRow(WeekOverview.this);
                            TextView txt1= new TextView(WeekOverview.this);
                            TextView txt2= new TextView(WeekOverview.this);
                            //setting the text
                            txt1.setText(swtch.getType());
                            txt2.setText(swtch.getTime());
                            txt1.setLayoutParams(params1);
                            txt2.setLayoutParams(params1);
                            //the textviews have to be added to the row created
                            row.addView(txt1);
                            row.addView(txt2);
                            row.setLayoutParams(params2);
                            tbl.addView(row);
                        }
                    }

                    for (Switch swtch : wpg.data.get("Thursday")) {
                        if (swtch.getState()){
                            // setting styling of the rows and text
                            final TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                            final TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                            final TableLayout tbl=(TableLayout) findViewById(R.id.tablethur);
                            //Creating new tablerows and textviews
                            TableRow row= new TableRow(WeekOverview.this);
                            TextView txt1= new TextView(WeekOverview.this);
                            TextView txt2= new TextView(WeekOverview.this);
                            //setting the text
                            txt1.setText(swtch.getType());
                            txt2.setText(swtch.getTime());
                            txt1.setLayoutParams(params1);
                            txt2.setLayoutParams(params1);
                            //the textviews have to be added to the row created
                            row.addView(txt1);
                            row.addView(txt2);
                            row.setLayoutParams(params2);
                            tbl.addView(row);
                        }
                    }

                    for (Switch swtch : wpg.data.get("Friday")) {
                        if (swtch.getState()){
                            // setting styling of the rows and text
                            final TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                            final TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                            final TableLayout tbl=(TableLayout) findViewById(R.id.tablefri);
                            //Creating new tablerows and textviews
                            TableRow row= new TableRow(WeekOverview.this);
                            TextView txt1= new TextView(WeekOverview.this);
                            TextView txt2= new TextView(WeekOverview.this);
                            //setting the text
                            txt1.setText(swtch.getType());
                            txt2.setText(swtch.getTime());
                            txt1.setLayoutParams(params1);
                            txt2.setLayoutParams(params1);
                            //the textviews have to be added to the row created
                            row.addView(txt1);
                            row.addView(txt2);
                            row.setLayoutParams(params2);
                            tbl.addView(row);
                        }
                    }

                    for (Switch swtch : wpg.data.get("Saturday")) {
                        if (swtch.getState()){
                            // setting styling of the rows and text
                            final TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                            final TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                            final TableLayout tbl=(TableLayout) findViewById(R.id.tablemon);
                            //Creating new tablerows and textviews
                            TableRow row= new TableRow(WeekOverview.this);
                            TextView txt1= new TextView(WeekOverview.this);
                            TextView txt2= new TextView(WeekOverview.this);
                            //setting the text
                            txt1.setText(swtch.getType());
                            txt2.setText(swtch.getTime());
                            txt1.setLayoutParams(params1);
                            txt2.setLayoutParams(params1);
                            //the textviews have to be added to the row created
                            row.addView(txt1);
                            row.addView(txt2);
                            row.setLayoutParams(params2);
                            tbl.addView(row);
                        }
                    }

                    for (Switch swtch : wpg.data.get("Sunday")) {
                        if (swtch.getState()){
                            // setting styling of the rows and text
                            final TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                            final TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                            final TableLayout tbl=(TableLayout) findViewById(R.id.tablesun);
                            //Creating new tablerows and textviews
                            TableRow row= new TableRow(WeekOverview.this);
                            TextView txt1= new TextView(WeekOverview.this);
                            TextView txt2= new TextView(WeekOverview.this);
                            //setting the text
                            txt1.setText(swtch.getType());
                            txt2.setText(swtch.getTime());
                            txt1.setLayoutParams(params1);
                            txt2.setLayoutParams(params1);
                            //the textviews have to be added to the row created
                            row.addView(txt1);
                            row.addView(txt2);
                            row.setLayoutParams(params2);
                            tbl.addView(row);
                        }
                    }

                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
    }
}