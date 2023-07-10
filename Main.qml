import QtQuick
import QtQuick.Window
import QtQuick.Controls
import "counterlib.mjs" as CounterLib

Window {
    width: 640
    height: 480
    visible: true
    title: "Counter"

    property var counter: new CounterLib.Counter()

    Component.onCompleted: {
        counter.state.observe((state) => {
            countLabel.text = `Count is ${state.count}`
        })
    }

    Component.onDestruction: counter.state.unsubscribeAll()

    Column {
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter

        Label {
            id: countLabel
            text: "Count is 0"
        }
        Button {
            text: "Increment"
            onClicked: counter.increment()
        }
    }
}
