package com.antigua.mynoteroom.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.antigua.mynoteroom.R

@Composable
fun NotePopup(
    txtState: MutableState <String> = rememberSaveable { mutableStateOf("")},
    onClickSave: (String) -> Unit,
    onClickDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onClickDismiss) {
       Column(
           modifier = Modifier
               .clip(RoundedCornerShape(8.dp))
               .background(color = MaterialTheme.colorScheme.background)
       ) {
           BasicTextField(
               modifier =Modifier
                   .padding(start = 16.dp ,end = 16.dp , top = 24.dp , bottom = 10.dp),
               value = txtState.value,
               onValueChange = { txt ->
                    txtState.value = txt
               })

           Row (
               modifier = Modifier.padding(start = 16.dp, end = 16.dp , bottom = 16.dp)
                   ){
             Button(onClick =  onClickDismiss ) {
                 Text(text = stringResource(R.string.screen_home_popup_button_dismiss))
             }
               Spacer(modifier = Modifier.width(8.dp))
             Button(onClick = { onClickSave( txtState.value ) }) {
                   Text(text = stringResource(R.string.screen_home_popup_button_save))
             }
           }
       }
    }
}