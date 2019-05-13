# NotAmazon
  Changelog (5/12/19)
  ```
  -SellPage is semi-completed.
  -TransactionPage is in progress.
  -SellPage needs time revision, amongst other minor unintended outcomes.
      -Resolved time revision, image URI still in progress.
  -Image URI link
  -Known Bugs on macOS discovered with the implementation of FileChooser
    "objc[24064]: Class FIFinderSyncExtensionHost is implemented in both
    /System/Library/PrivateFrameworks/FinderKit.framework/Versions/A/FinderKit (0x7fffa7174210) and
    /System/Library/PrivateFrameworks/FileProvider.framework/OverrideBundles/FinderSyncCollaborationFileProviderOverride.
  bundle/Contents/MacOS/FinderSyncCollaborationFileProviderOverride (0x12017fdc8). One of the two will be used. Which one is
  undefined. 2019-05-12 13:55:38.615 java[24064:5582934] warning: <NSRemoteView: 0x7ffc08e1a490> determined it was necessary
  to configure <GlassOpenPanel: 0x7ffc04ff1be0> to support remote view vibrancy"
  
  -Has been determined to not affect functionality in any visible means.
  -See https://bugs.openjdk.java.net/browse/JDK-8189083 for more details.
  ```
  Changelog (5/11/19)
  ```
  -Pending Applications Page is completed. (PendAppPage)
  -Testing is done on PendAppPage
  -Fixed Logging in without password.
  -Added defaultPassword upon approval of a new User's application.
  ```
  Changelog (4/29/19)
  ```
  -Added FirstScene,SecondScene, and ThirdScene as shown prototypes.
  ```
  Changelog (4/19/19)
  ```
  -Changes to design goal, if an admin sends a warning, the user will recieve a dialog 
  and the warning can be read in messages.
  -Changes to changelog for clarity.
  ```
  
  Changelog (4/15/19)
  ```
  -Discussed design constraints, images, main page display, and user-specific pages.
  -Evaluate the GUI for any needed changes.
  -Organized documents into a single folder.   
  -Upload code, scenes will be split into their own code (FOR NOW) to ensure stability 
  before committing to the main files.
  ```
