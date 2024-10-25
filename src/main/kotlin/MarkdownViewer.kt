import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
fun MarkdownViewer() {
    //Mutable state to store the file content
    var fileContent by remember { mutableStateOf("No file selected") }

    Box(modifier = Modifier.fillMaxSize()) {
        //Remember the scroll state
        val scrollState = rememberScrollState()

        //Column to stack the components vertically
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val fileChooser = JFileChooser()
                //Only allow selection of files with .md extension
                fileChooser.fileFilter = FileNameExtensionFilter("Markdown files", "md")
                val result = fileChooser.showOpenDialog(null)
                if (result == JFileChooser.APPROVE_OPTION) {
                    val file: File = fileChooser.selectedFile
                    //Check file extension, if invalid display error message
                    if (file.extension != "md") {
                        fileContent = "Invalid file type, please select a valid Markdown (.md) file"
                    } else {
                        fileContent = file.readText()
                    }
                }

            }
            ) {
                Text("Upload Markdown file")
            }

            Box(modifier = Modifier.verticalScroll(scrollState)) {
                BasicTextField(
                    value = fileContent,
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp, end = 16.dp)
                )
            }

            //Vertical scrollbar to accompany scrollable content
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(scrollState),
                modifier = Modifier.fillMaxHeight()
            )

        }
    }


}
