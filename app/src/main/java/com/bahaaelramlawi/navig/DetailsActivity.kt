package com.bahaaelramlawi.navig

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bahaaelramlawi.navig.model.Movies
import com.example.projectlistview.db.MoviesDataBaseHandler
import kotlinx.android.synthetic.main.activity_movies_details.*


class DetailsActivity : AppCompatActivity()
    {

        var fav_saved : Boolean = false
        var bok_saved : Boolean = false
        val db_favorite:MoviesDataBaseHandler = MoviesDataBaseHandler(this, "favorite")
        val db_booking:MoviesDataBaseHandler = MoviesDataBaseHandler(this, "booking")

        override fun onCreate(savedInstanceState: Bundle?)
            {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_movies_details)
                var movie : Movies = Movies("null", "null", "null", R.drawable.a2)


                    txtName.text = intent.getStringExtra("title")
                    txtYear.text = intent.getStringExtra("year")
                    txtinfo.text = intent.getStringExtra("info")
                    image.setImageResource(intent.getIntExtra("image", R.drawable.b7))

                    movie = Movies(intent.getStringExtra("title"), intent.getStringExtra("year"), intent.getStringExtra("info"), intent.getIntExtra("image", R.drawable.b7))

                ////////////////////////////////////////////////////////////////////////////////////////////
                //btnBooking used Save and delete data
                btnBooking.setOnClickListener {
                    //If it is not saved it is saved when clicked
                    if(!bok_saved)
                        {
                            save_to_database(movie, db_booking)
                            bok_saved = true
                        }
                    //If the deletion of the data will make the save is false
                    else
                        {
                            remove_from_database(movie, db_booking)
                            bok_saved = false
                        }
                }
                ////////////////////////////////////////////////////////////////////////////////////////////

                //ForLoop for To save data in booking.
                for(user in db_booking.getMovies())
                    {
                        //If the movie is saved in the Ticektbooking message, the message "Saved" appears
                        if(user.name.equals(movie.name))
                            {
                                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
                                bok_saved = true
                            }
                    }
                //ForLoop for To save data in favorite.
                for(user in db_favorite.getMovies())
                    {
                        Toast.makeText(this, intent.getStringExtra("title"), Toast.LENGTH_SHORT).show()
                            //If the movie is saved in the favorite message, the message "Saved" appears
                            if(user.name.equals(movie.name))
                                {
                                    Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
                                    fav_saved = true
                                }
                    }

                ////////////////////////////////////////////////////////////////////////////////////////////

                //If the movie is saved successfully, the tag is active
                if(fav_saved)
                    {
                        favorite.setImageResource(R.drawable.ic_stare_saved)
                    }
                //If not, saving the movie makes the mark inactive
                else
                    {
                        favorite.setImageResource(R.drawable.ic_stare)
                    }

                favorite.setOnClickListener {
                    //When you click on the tag to delete the movie data, the tag is converted to inactive
                    if (fav_saved)
                        {
                            remove_from_database(movie, db_favorite)
                            fav_saved = false
                            favorite.setImageResource(R.drawable.ic_stare)
                        }
                    //Otherwise, it will be saved
                    else
                        {
                            save_to_database(movie, db_favorite)
                            fav_saved = true
                            favorite.setImageResource(R.drawable.ic_stare_saved)
                        }
                }
            ////////////////////////////////////////////////////////////////////////////////////////

            }

        ////////////////////////////////////////////////////////////////////////////////////////////
        //Function for Delete movie from databases.
        private fun remove_from_database(user: Movies, db: MoviesDataBaseHandler)
            {
                db.deleteUser(user)
                //To print a message meaning deleted
                Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show()
            }

        //Function for save movie from databases.
        private fun save_to_database(user: Movies, db: MoviesDataBaseHandler)
            {
                db.addUser(user)
                //To print a message meaning saved
                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
            }
        ////////////////////////////////////////////////////////////////////////////////////////////

    }