package cmov_68017.rickandmortyfirebase.data.network

import cmov_68017.rickandmortyfirebase.data.model.SavedItem
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val database = FirebaseDatabase.getInstance().reference

    fun incrementRollCount() {
        database.child("stats").child("rollCount")
            .runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    val current = mutableData.getValue(Int::class.java) ?: 0
                    mutableData.value = current + 1
                    return Transaction.success(mutableData)
                }
                override fun onComplete(error: DatabaseError?, committed: Boolean, snapshot: DataSnapshot?) {}
            })
    }

    fun observeRollCount(onCountUpdated: (Int) -> Unit) {
        database.child("stats").child("rollCount")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val count = snapshot.getValue(Int::class.java) ?: 0
                    onCountUpdated(count)
                }
                override fun onCancelled(error: DatabaseError) {}
            })
    }

    // saves the item to the database
    suspend fun saveItem(item: SavedItem) {
        database.child("lastSavedItem").setValue(item).await()
        database.child("history").push().setValue(item).await()
    }

    // retrieves the last saved item from the database
    suspend fun getLastSavedItem(): SavedItem? {
        val snapshot = database.child("lastSavedItem").get().await()
        return snapshot.getValue(SavedItem::class.java)
    }

}