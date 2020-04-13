package com.example.firebase;






import android.os.AsyncTask;


import android.util.Log;











public class LongOperation extends AsyncTask<Void, Void, String> {


    @Override


    protected String doInBackground(Void... params) {


        try {




            GMailSender sender = new GMailSender("shwetalishimangaud@gmail.com", " I LOVEHATE U");


            sender.sendMail("Industry Requirement",


                    "farmer wants to sell his produce to you","shwetalishimangaud@gmail.com", String.valueOf(PostDetailActivity.mail));





        } catch (Exception e) {


            Log.e("error", e.getMessage(), e);

            return "Email Not Sent";
        }
        return "Email Sent";
    }

    @Override


    protected void onPostExecute(String result) {





        Log.e("LongOperation",result+"");


    }





    @Override


    protected void onPreExecute() {


    }





    @Override


    protected void onProgressUpdate(Void... values) {


    }


}


