package lihan.chen.wordpressahan.feature.lazycolumnlaggy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LaggyLazyColumn() {
    Box(modifier = Modifier.fillMaxSize()){
//        LazyColumn(
//            modifier = Modifier.fillMaxSize().padding(8.dp)
//        ){
//            items(1000 , key = {it.hashCode()}){
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(4.dp),
//                    text = "Text $it",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(5.dp)
        ) {
            repeat(1000){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = "Text $it",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }
    }
}

@Preview
@Composable
fun LaggyLazyColumnPreview() {
    LaggyLazyColumn()

}