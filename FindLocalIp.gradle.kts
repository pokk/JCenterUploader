// Get the ip address by interface name
// en0 is WIFI interface
fun getLocalIp(interfaceName: String) = NetworkInterface.getByName(interfaceName)
    ?.interfaceAddresses
    ?.find { it.address.hostAddress.length <= 15 }
    ?.address
    ?.hostAddress
    .orEmpty()
