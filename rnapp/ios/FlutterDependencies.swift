import Flutter
import FlutterPluginRegistrant

class FlutterDependencies {
  let flutterEngine: FlutterEngine
  
  init() {
    flutterEngine = FlutterEngine(name: "default")
    setupEngine()
  }
  
  private func setupEngine() {
    self.flutterEngine.run()
    GeneratedPluginRegistrant.register(with: self.flutterEngine)
  }
}
