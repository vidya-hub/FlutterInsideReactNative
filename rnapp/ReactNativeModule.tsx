import {NativeModules} from 'react-native';

const {ReactNativeModule} = NativeModules;

// Define the types for the FlutterBridge module
interface RnModuleType {
  callFlutterMethod: (method: string, args: any) => Promise<any>;
}

// Type the NativeModules import
const RnFlutterModule: RnModuleType = ReactNativeModule;

// Export the typed module
export default {
  callFlutterMethod: (method: string, args: any): Promise<any> => {
    return RnFlutterModule.callFlutterMethod(method, args);
  },
};
