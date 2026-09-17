package com.ergi.jualan.ui.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HubungiKamiScreen(navController: NavController?) {
    var emailText by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hubungi Kami") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = "Back Icon"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hubungi Kami",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = emailText,
                onValueChange = { emailText = it },
                label = { Text("Email Anda") },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                label = { Text("Pesan") },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = "Pesan Terkirim")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text("Kirim Pesan", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}