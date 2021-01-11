package com.assignment.api;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.DefaultRetryPolicy;


import com.assignment.AppController;
import com.assignment.model.ListModel;


public class MovielistAPI {

    public interface MovielistAPIListListener {
        void requestSuccessful(ArrayList<ListModel> items);

        void onLoadFail();
    }

    private MovielistAPI.MovielistAPIListListener mMovielistAPIListListener;

    public MovielistAPI(MovielistAPIListListener MovielistAPIListListener) {
        mMovielistAPIListListener = MovielistAPIListListener;
    }


    public void getMovieList(final Context context, String url) {


        try {
            JsonObjectRequest stringRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObjectResult) {
                            try {
                                ArrayList<ListModel> Models1 = new ArrayList<ListModel>();

                                Log.d("response_", "res" + jsonObjectResult.toString());
                                if (jsonObjectResult.has("results")) {
                                    JSONArray jsonArray = jsonObjectResult.getJSONArray("results");
                                    if (jsonObjectResult.getJSONArray("results").length() > 0) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            ListModel model = new ListModel();
                                            if (jsonObject.has("id")) {
                                                model.id = jsonObject.getInt("id");
                                                Log.d("id", "res" + model.id);

                                            }
                                            if (jsonObject.has("title")) {
                                                model.title = jsonObject.getString("title");
                                                Log.d("title", "res" + model.title);

                                            }

                                            if (jsonObject.has("overview")) {
                                                model.overview = jsonObject.getString("overview");
                                                Log.d("overview", "res" + model.overview);

                                            }

                                            if (jsonObject.has("poster_path")) {
                                                model.poster_path = jsonObject.getString("poster_path");
                                                Log.d("poster_path", "res" + model.poster_path.toString());

                                            }

                                            if (jsonObject.has("release_date")) {
                                                model.release_date = jsonObject.getString("release_date");
                                            }

                                            if (jsonObject.has("original_language")) {
                                                model.language = jsonObject.getString("original_language");
                                            }

                                            Models1.add(model);
                                        }
                                    }
                                }

                                mMovielistAPIListListener.requestSuccessful(Models1);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mMovielistAPIListListener.onLoadFail();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mMovielistAPIListListener.onLoadFail();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("Content-Type", "application/json; charset=utf-8");

                    return map;
                }

            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mMovielistAPIListListener.onLoadFail();
            e.printStackTrace();
        }
    }


}
