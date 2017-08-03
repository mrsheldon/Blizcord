package me.bleuzen.blizcord;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class GUI_Config extends JFrame {

	static GUI_Config instance;

	private final File configFile;

	private final JTextField bottoken;
	private final JTextField controlchannel;
	private final JTextField commandprefix;
	private final JTextField voicechannel;
	private final JTextField adminsrole;
	private final JCheckBox display_song_as_game;
	private final JCheckBox update_check_box;
	private final JSpinner update_check_interval_hours_spinner;
	private final JLabel lblIntervalInHours;
	private final JButton btnGet;
	private final JCheckBox chckbxAdminsRole;
	private final JCheckBox chckbxCustomVolume;
	private final JSpinner spinnerVolume;
	private final JCheckBox chckbxEnableMediaControl;

	public GUI_Config(File config) {
		instance = this;

		configFile = config;

		setType(Type.UTILITY);
		setTitle(configFile.getName());
		setResizable(false);
		setSize(360, 360);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		if(GUI.isIconSet()) {
			setIconImage(GUI.getIcon());
		}

		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 4, 348, 322);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel bottokenlable = new JLabel("Bot token:");
		bottokenlable.setBounds(12, 8, 120, 28);
		panel.add(bottokenlable);

		JLabel controlchannellable = new JLabel("Control channel:");
		controlchannellable.setToolTipText("The channel where you can send commands to the bot");
		controlchannellable.setBounds(12, 38, 120, 28);
		panel.add(controlchannellable);

		JLabel commandprefixlable = new JLabel("Command prefix:");
		commandprefixlable.setBounds(10, 68, 120, 28);
		panel.add(commandprefixlable);

		bottoken = new JTextField();
		bottoken.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(bottoken.getText().length() < 2) {
					hideGetButton();
				}
			}
		});
		bottoken.setBounds(140, 8, 206, 28);
		panel.add(bottoken);

		commandprefix = new JTextField();
		commandprefix.setBounds(140, 68, 206, 28);
		panel.add(commandprefix);

		JLabel voicechannellable = new JLabel("Voice channel:");
		voicechannellable.setToolTipText("The voice channel which the bot will join");
		voicechannellable.setBounds(10, 98, 120, 28);
		panel.add(voicechannellable);

		voicechannel = new JTextField();
		voicechannel.setBounds(140, 98, 206, 28);
		panel.add(voicechannel);

		adminsrole = new JTextField();
		adminsrole.setBounds(140, 128, 206, 28);
		panel.add(adminsrole);

		display_song_as_game = new JCheckBox("Display song as game");
		display_song_as_game.setToolTipText("Displays the name of the current song as game on Discord");
		display_song_as_game.setBounds(10, 158, 330, 28);
		panel.add(display_song_as_game);

		update_check_box = new JCheckBox("Update check");
		update_check_box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update_check_interval_hours_spinner.setEnabled(update_check_box.isSelected());
			}
		});
		update_check_box.setBounds(10, 188, 130, 28);
		panel.add(update_check_box);

		update_check_interval_hours_spinner = new JSpinner();
		update_check_interval_hours_spinner.setEnabled(false);
		update_check_interval_hours_spinner.setModel(new SpinnerNumberModel(24, 1, null, 1));
		update_check_interval_hours_spinner.setBounds(276, 188, 64, 28);
		panel.add(update_check_interval_hours_spinner);

		lblIntervalInHours = new JLabel("Interval in hours:");
		lblIntervalInHours.setBounds(148, 188, 124, 28);
		panel.add(lblIntervalInHours);

		controlchannel = new JTextField();
		controlchannel.setBounds(140, 38, 206, 28);
		panel.add(controlchannel);

		btnGet = new JButton("Get");
		btnGet.setToolTipText(Values.DISCORD_GET_TOKEN);
		btnGet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideGetButton();

				try {
					Desktop.getDesktop().browse(new URI(Values.DISCORD_GET_TOKEN));
				} catch (Exception e1) {
					GUI.showErrMsgBox(e1.getMessage());
				}
			}
		});
		btnGet.setVisible(false);
		btnGet.setBounds(266, 8, 80, 28);
		panel.add(btnGet);

		chckbxAdminsRole = new JCheckBox("Admins role");
		chckbxAdminsRole.setToolTipText("Users in this role can control the bot");
		chckbxAdminsRole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxAdminsRole.isSelected()) {
					adminsrole.setEnabled(true);
				} else {
					adminsrole.setEnabled(false);
					adminsrole.setText("");
				}
			}
		});
		chckbxAdminsRole.setBounds(10, 128, 120, 28);
		panel.add(chckbxAdminsRole);

		chckbxCustomVolume = new JCheckBox("Custom Volume");
		chckbxCustomVolume.setToolTipText("Change the volume (other than 100% requires more CPU)");
		chckbxCustomVolume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerVolume.setEnabled(chckbxCustomVolume.isSelected());
			}
		});
		chckbxCustomVolume.setBounds(10, 218, 240, 28);
		panel.add(chckbxCustomVolume);

		spinnerVolume = new JSpinner();
		spinnerVolume.setEnabled(false);
		spinnerVolume.setModel(new SpinnerNumberModel(100, 5, 100, 5));
		spinnerVolume.setBounds(276, 218, 64, 28);
		panel.add(spinnerVolume);

		chckbxEnableMediaControl = new JCheckBox("Enable media control keys");
		//TODO: Update ToolTipText, when JNativeHook got a fix
		chckbxEnableMediaControl.setToolTipText("<html>If enabled you can use the media control keys on your keyboard to control the bot.<br>This will increase your CPU and RAM usage. Currently this does not work on all systems.</html>");
		chckbxEnableMediaControl.setBounds(10, 248, 262, 28);
		panel.add(chckbxEnableMediaControl);

		JButton btnApply = new JButton("Apply");
		btnApply.setBounds(248, 282, 90, 28);
		panel.add(btnApply);
		btnApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnApply.setEnabled(false); // prevent from double clicking

				Config.set(Config.BOT_TOKEN, bottoken.getText());
				Config.set(Config.CONTROL_CHANNEL, controlchannel.getText());
				Config.set(Config.COMMAND_PREFIX, commandprefix.getText());
				Config.set(Config.VOICE_CHANNEL, voicechannel.getText());
				Config.set(Config.ADMINS_ROLE, adminsrole.getText());
				Config.set(Config.DISPLAY_SONG_AS_GAME, String.valueOf(display_song_as_game.isSelected()));
				Config.set(Config.UPDATE_CHECK_INTERVAL_HOURS, (update_check_box.isSelected() ? update_check_interval_hours_spinner.getValue().toString() : "0"));
				Config.set(Config.VOLUME, (chckbxCustomVolume.isSelected() ? spinnerVolume.getValue().toString() : "100"));
				Config.set(Config.ENABLE_MEDIA_CONTROL_KEYS, String.valueOf(chckbxEnableMediaControl.isSelected()));

				if(Config.save()) {
					JOptionPane.showMessageDialog(instance, "Config saved.", Values.BOT_NAME, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(instance, "Failed to save config.", Values.BOT_NAME, JOptionPane.ERROR_MESSAGE);
				}
				dispose();

				GUI.mvToFront();
			}
		});

		read();
	}

	private void read() {
		Config.init(configFile, true);

		commandprefix.setText(Config.get(Config.COMMAND_PREFIX));
		controlchannel.setText(Config.get(Config.CONTROL_CHANNEL));
		display_song_as_game.setSelected(Boolean.parseBoolean(Config.get(Config.DISPLAY_SONG_AS_GAME)));
		chckbxEnableMediaControl.setSelected(Boolean.parseBoolean(Config.get(Config.ENABLE_MEDIA_CONTROL_KEYS)));
		voicechannel.setText(Config.get(Config.VOICE_CHANNEL));

		if(a.isDisableUpdateChecker()) {
			update_check_box.setEnabled(false);
		} else {
			int updateH = Integer.parseInt(Config.get(Config.UPDATE_CHECK_INTERVAL_HOURS));
			if (updateH != 0) {
				update_check_box.setSelected(true);
				update_check_interval_hours_spinner.setEnabled(true);
				update_check_interval_hours_spinner.setValue(updateH);
			}
		}

		int vol = Integer.parseInt(Config.get(Config.VOLUME));
		if (vol != 100) {
			chckbxCustomVolume.setSelected(true);
			spinnerVolume.setEnabled(true);
			spinnerVolume.setValue(vol);
		}

		String token = Config.get(Config.BOT_TOKEN);
		if(token.isEmpty()) {
			bottoken.setSize(124, bottoken.getHeight());
			btnGet.setVisible(true);
		} else {
			bottoken.setText(token);
		}

		String adminsRoleStr = Config.get(Config.ADMINS_ROLE);
		if(adminsRoleStr.isEmpty()) {
			adminsrole.setEnabled(false);
		} else {
			adminsrole.setText(adminsRoleStr);
			chckbxAdminsRole.setSelected(true);
		}

	}

	private void hideGetButton() {
		btnGet.setVisible(false);
		bottoken.setSize(206, bottoken.getHeight());
	}
}