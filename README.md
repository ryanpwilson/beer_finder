# beer finder

# A demo application for fetching, parsing and displaying beer data from the PunkAPI 

models:
The primary data model is contained in the Beer data class, this is defined based on the shape of
the data presented by the API at "https://api.punkapi.com/v2/beers"

Contained in models are various child classes defining the sub types found in a Beer object

Activities:

MainActivity: This is a simple activity containing a button and loading view, clicking the button 
send a request via NetworkUtils to fetch the initial beer data. Once loaded, the app will navigate
to a second screen automatically to diplay the list.

DisplayListActivity: This activity holds a RecyclerView responsible for displaying the beer data.
This activity is bound to the MainViewModel responsible for presenting data via the BeerListAdapter
It accepts data presented to it via the api and parses it into a list of Beer objects that are then
added to the data model. 

Helpers:
MainViewModel: The backing data array for the Beer List data, items can be added and removed.

BeerListAdapter: Binds the data fields present in Beer objects to the BeerViewHolder, this is 
presented as a CardView within the parent Recycler view in the DisplayListActivity. It extends an
onCLickListener responsible for showing and hiding detail data when a row in the list is selected.

NetworkUtils: Two helper methods to fetch the full set of beer data from the API, then the
individual images for each beer logo.!

[ezgif com-gif-maker](https://user-images.githubusercontent.com/9808737/136595486-5b9a6915-706a-4025-9f9b-076045e74204.gif)
