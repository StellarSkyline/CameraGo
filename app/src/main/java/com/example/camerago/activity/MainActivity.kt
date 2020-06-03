package com.example.camerago.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.camerago.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var bottomView:View
    private val CAMERA_REQUEST_CODE = 100
    private val READWRITE_REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
    }

    private fun init() {
        bottomView = layoutInflater.inflate(R.layout.bottom_sheet, null)
        button_fab.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.button_fab -> {showBottomSheet()}
            R.id.button_camera -> {checkCameraPermission()}
            R.id.button_gallery -> {checkGalleryPermission()}

        }
    }

    fun showBottomSheet() {
        bottomView = layoutInflater.inflate(R.layout.bottom_sheet, null)
        bottomView.button_camera.setOnClickListener(this)
        bottomView.button_gallery.setOnClickListener(this)
        var dialog = BottomSheetDialog(this)
        dialog.setContentView(bottomView)
        dialog.show()

    }

    fun openCamera() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    private fun openGallery() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, READWRITE_REQUEST_CODE)
    }

    fun checkCameraPermission() {
        var permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
        } else {
            openCamera()
        }
    }

    fun checkGalleryPermission() {
        var permission = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)

        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),READWRITE_REQUEST_CODE)
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),READWRITE_REQUEST_CODE)
        } else {
            openGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                var item = data!!.extras!!.get("data") as Bitmap
                image_view.setImageBitmap(item)
            }

            READWRITE_REQUEST_CODE -> {
                image_view.setImageURI(data?.data)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                    openCamera()
                }
            }

            READWRITE_REQUEST_CODE -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                    openGallery()
                }
            }
        }
    }


}