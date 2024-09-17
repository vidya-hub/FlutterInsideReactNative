import Flutter
import Foundation

class FlutterCacheManager {

    // Singleton instance of the manager
    static let shared = FlutterCacheManager()

    // Optional FlutterDependencies and FlutterMethodChannel variables
    private var dependencies: FlutterDependencies? = nil
    private var methodChannel: FlutterMethodChannel? = nil
    private var methodChannelName :String = "com.vidya.fluttermodule"
  
    // Private init ensures that this class can only be instantiated through the `shared` instance
    private init() {}

    // Method to get or create the FlutterDependencies instance
    func getOrCreateDependency() -> FlutterDependencies {
        // Return existing dependencies if available, otherwise create a new instance
        if let existingDependencies = dependencies {
            return existingDependencies
        } else {
            let flutterDependencies = FlutterDependencies()
            dependencies = flutterDependencies
            return flutterDependencies
        }
    }

    // Method to get or create the FlutterMethodChannel
    func getOrCreateMethodChannel(for engine: FlutterEngine) -> FlutterMethodChannel {
        // Return existing method channel if available, otherwise create a new one
        if let existingChannel = methodChannel {
            return existingChannel
        } else {
            let newMethodChannel = FlutterMethodChannel(name: methodChannelName, binaryMessenger: engine.binaryMessenger)
            methodChannel = newMethodChannel
            return newMethodChannel
        }
    }
}
