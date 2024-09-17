import Flutter
import React

// Expose the ReactNativeModule class to React Native with the name 'ReactNativeModule'
@objc(ReactNativeModule)
class ReactNativeModule: NSObject {
    private var methodChannel: FlutterMethodChannel?
    let flutterDependencies: FlutterDependencies

    // Initialize the ReactNativeModule
    override init() {
        flutterDependencies = FlutterCacheManager.shared.getOrCreateDependency()
        super.init()
        initializeMethodChannel()
    }

    // Method to call a Flutter method from React Native
    @objc
    func callFlutterMethod(_ method: String, arguments: Any, resolver: @escaping RCTPromiseResolveBlock, rejecter: @escaping RCTPromiseRejectBlock) {
        // Check if the method channel is initialized
        guard let methodChannel = methodChannel else {
            rejecter("error", "Method channel not initialized", nil)
            return
        }
        // Execute the Flutter method on the main thread
        DispatchQueue.main.async {
            // Invoke the specified method on the Flutter side with arguments
            methodChannel.invokeMethod(method, arguments: arguments) { (response) in
                if let error = response as? FlutterError {
                    rejecter("error", error.message, nil)
                } else if let result = response {
                    resolver(result)
                } else {
                    resolver(NSNull())
                }
            }
        }
    }

    // to initialize the Flutter method channel
    private func initializeMethodChannel() {
        methodChannel = FlutterCacheManager.shared.getOrCreateMethodChannel(for: flutterDependencies.flutterEngine)
    }
}
