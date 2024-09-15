import React, {useRef} from 'react';
import {ViewProps} from 'react-native';
import {requireNativeComponent, UIManager} from 'react-native';

// Define the props for the component
interface FlutterViewProps extends ViewProps {}

// Create the FlutterView component
const ComponentName = 'FlutterComponentView';

let NativeFlutterView: React.ComponentType<FlutterViewProps> | null = null;

const initializeFlutterView = () => {
  if (!NativeFlutterView) {
    // Check if the native component is available
    if (!UIManager.getViewManagerConfig(ComponentName)) {
      throw new Error(
        `The package '${ComponentName}' doesn't seem to be linked. Make sure: \n\n` +
          '- You rebuilt the app after installing the package\n' +
          '- You are not using Expo Go\n',
      );
    }
    console.log('Initializing Flutter View');
    NativeFlutterView = requireNativeComponent<FlutterViewProps>(ComponentName);
  }
  return NativeFlutterView;
};

const FlutterView: React.FC<FlutterViewProps> = props => {
  const FlutterViewRef = useRef(initializeFlutterView());

  return <FlutterViewRef.current {...props} />;
};

export default FlutterView;
