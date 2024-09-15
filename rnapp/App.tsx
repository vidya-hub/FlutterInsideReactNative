import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  View,
  TouchableOpacity,
  Text,
} from 'react-native';
import ReactNativeModule from './ReactNativeModule';
import FlutterView from './FlutterView';

function App(): React.JSX.Element {
  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.headerText}>Flutter Inside React Native</Text>
      <View style={styles.centeredView}>
        <View style={styles.flutterContainer}>
          <FlutterView style={styles.flutterView} />
        </View>
        <TouchableOpacity
          onPress={() => {
            ReactNativeModule.callFlutterMethod('animate', null);
          }}
          style={styles.recButton}>
          <Text style={styles.buttonText}>{'Animate'}</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  headerText: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginVertical: 20,
  },
  centeredView: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  flutterContainer: {
    borderWidth: 1,
    borderColor: 'red',
    margin: 20,
    borderRadius: 2,
  },
  flutterView: {
    height: 300,
    width: 400,
  },
  recButton: {
    backgroundColor: 'red',
    width: 150,
    height: 50,
    borderRadius: 20,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 20,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 17,
  },
});

export default App;
