package lihan.chen.wordpressahan.feature.imagepicker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore.PickerMediaColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.io.File
import java.io.FileFilter
import java.util.Objects

@Composable
fun ImagePicker() {
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var imageUriCamera by remember {
        mutableStateOf<Uri?>(null)
    }
    val file = File.createTempFile(
        "imageName",
        ".jpg",
        context.externalCacheDir
    )
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider" , file
    )
    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            imageUri = it
        }
    )
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            imageUriCamera = uri
        }
    )
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it){
                cameraLauncher.launch(uri)
            }else{
                // Permission Denied
            }
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            pickerLauncher.launch(
               PickVisualMediaRequest(
                   ActivityResultContracts.PickVisualMedia.ImageOnly
               )
            )
        }) {
            Text(text = "Image Pick")
        }
        imageUri?.let {
            Image(
                modifier = Modifier.size(250.dp),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = it)
                        .build()
                ),
                contentDescription = "async image"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val permissionCheckResult = ContextCompat.checkSelfPermission(
                context,Manifest.permission.CAMERA
            )
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                cameraLauncher.launch(uri)
            }else{
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }) {
            Text(text = "Take Photo")
        }
        imageUriCamera?.let {
            Image(
                modifier = Modifier.size(250.dp),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = it)
                        .build()
                ),
                contentDescription = "async image"
            )
        }
    }



}

@Preview
@Composable
fun ImagePickerPreview() {
    ImagePicker()

}