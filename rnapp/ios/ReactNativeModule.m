#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ReactNativeModule, NSObject)

// Expose the method 'callFlutterMethod' to React Native
RCT_EXTERN_METHOD(callFlutterMethod:(NSString *)method
                  arguments:(id)arguments
                  resolver:(RCTPromiseResolveBlock)resolver
                  rejecter:(RCTPromiseRejectBlock)rejecter)

@end
