import Flutter
import React

@objc(FlutterComponentView)
class FlutterComponentView: RCTViewManager {
    
    static let shared = FlutterComponentView()
    
    override func view() -> UIView! {
        NSLog("Flutter View is triggered")
        
        // Create the container view
        let containerView = UIView(frame: UIScreen.main.bounds)
      
        DispatchQueue.main.async {
            // Get or create Flutter dependencies
            let flutterDependencies = FlutterCacheManager.shared.getOrCreateDependency()
            let flutterEngine = flutterDependencies.flutterEngine
            
            // Initialize the FlutterViewController
            let flutterViewController = FlutterViewController(engine: flutterEngine, nibName: nil, bundle: nil)
            
            // Add the Flutter view to the container view
            containerView.addSubview(flutterViewController.view)
            
            // Set up constraints or frame for the Flutter view
            flutterViewController.view.frame = containerView.bounds
            flutterViewController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
            
            // Optionally, add any additional setup for layout
            flutterViewController.view.setNeedsLayout()
            flutterViewController.view.layoutIfNeeded()
        }
        
        return containerView
    }

    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
}
