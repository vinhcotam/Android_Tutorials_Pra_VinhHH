package com.sun.android.ex_slide19

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sun.android.databinding.ActivityContentProviderBinding

class ContentProviderActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityContentProviderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_CONTACTS
                )
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_CONTACTS), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_CONTACTS), 1
                )
            }
        }
        val contactList: MutableList<ContactClass> = ArrayList()
        val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        if (contacts != null) {
            while (contacts.moveToNext()) {
                val checkNameValid = contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val checkNumberValid = contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                if (checkNameValid >= 0 && checkNumberValid > 0) {
                    val name = contacts.getString(checkNameValid)
                    val number = contacts.getString(checkNumberValid)
                    val obj = ContactClass(name, number)
                    contactList.add(obj)
                }
            }
        }
        binding.recyclerViewContact.adapter = ContactListAdapter(contactList)
        contacts?.close()
    }
}
