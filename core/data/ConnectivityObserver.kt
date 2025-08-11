class ConnectivityObserver(private val context: Context) {

    enum class Status { Available, Unavailable, Lost }

    fun observe(): Flow<Status> = callbackFlow {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(Status.Available)
            }

            override fun onLost(network: Network) {
                trySend(Status.Lost)
            }

            override fun onUnavailable() {
                trySend(Status.Unavailable)
            }
        }

        cm.registerNetworkCallback(request, callback)
        awaitClose { cm.unregisterNetworkCallback(callback) }
    }
}