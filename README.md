![Screenshot](https://github.com/appchief/Challenge001/blob/main/assets/cover.png)

### Task Description
You are developing a graphic design app, picking colors is essentails in such apps, but your color picker should add more value to stand out among your competitors, that's why you decided to support picking __gradients__ and __images__ as well, and to make this feature less boring you decided to update it periodically from a remote server.

The design as shown below and server response are as in the [json file here](https://github.com/malaa101/Challenge001/blob/master/app/src/main/assets/data.json).

[Demo video](https://github.com/malaa101/Challenge001/blob/master/app/src/main/assets/demo_1.mp4)



## Notes:

#### Fetching Data
- :white_check_mark: Don't write network layer, just load the json from disk
- :white_check_mark: Write proper decoding models

#### Layout
- :white_check_mark: We have two horizontal scrollable parts (Palettes & Palette Items)
- :white_check_mark: Palette items have special part in the begining, where user can pick advanced color, transparent color or use the eye-drop tool to pick any color on screen.
- :white_check_mark: Pallet items section has dynamic number of rows (can have up to three rows).

#### Interactions
Please make sure to implement the following interactions (as shown in the attached video)
- :white_check_mark: Tapping a Palette title should scroll items to that Palette
- :white_check_mark: Scrolling Items should change Palette selection and move it to screen if it's outside screen
- Tapping item should show selection indicator with animation
