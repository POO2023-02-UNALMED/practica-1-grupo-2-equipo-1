package com.ecart.uiMain.menus.userMenu.manageBankAccount;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;

import com.ecart.gestorAplicacion.entites.Person;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.Commons;
import com.ecart.uiMain.menus.userMenu.goShopping.GoShopping;
import com.ecart.uiMain.menus.userMenu.manageStores.manageStores;
import com.ecart.uiMain.menus.userMenu.suggestProducts.SuggestProducts;
import com.ecart.uiMain.menus.userMenu.viewShoppingCart.ViewShoppingCart;

final public class ManageBankAccount {
	public static void withdrawMoney(User user) {
		Renderer.drawBanner("withdraw money");
		center("==== Current Balance: " + String.valueOf(user.getBankAccount().getBalance()) + " ====", true);
		print(2);

		String[] r = questionnaire(
				new String[] {
						"💁 Password (same as your account)",
						"💸 Ammount to withdraw",
				});

		if (r[0].equals("") || r[1].equals("")) {
			Commons.dialog(new Retval("Not enough input was provided", false));
			return;
		}

		if (!user.getBankAccount().validate(r[0])) {
			Commons.dialog(new Retval("Wrong password", false));
			return;
		}

		double moneyToWithdraw = 0;
		try {
			moneyToWithdraw = Double.parseDouble(r[1]);
		} catch (NumberFormatException e) {
			Commons.dialog(new Retval(
					"Failed to parse your input. Make sure you are not typing letters in the place of numbers",
					false));
			return;
		}

		Retval retval = user.getBankAccount().withdraw(moneyToWithdraw);
		Commons.dialog(retval);
	}

	public static void depositMoney(User user) {
		Renderer.drawBanner("deposit money");
		center("==== Current Balance: " + String.valueOf(user.getBankAccount().getBalance()) + " ====", true);
		print(2);


		String[] r = questionnaire(
				new String[] {
						"💁 Password (same as your account)",
						"💸 Ammount to deposit",
				});

		if (r[0].equals("") || r[1].equals("")) {
			Commons.dialog(new Retval("Not enough input was provided", false));
			return;
		}

		if (!user.getBankAccount().validate(r[0])) {
			Commons.dialog(new Retval("Wrong password", false));
			return;
		}

		double moneyToDeposit = 0;
		try {
			moneyToDeposit = Double.parseDouble(r[1]);
		} catch (NumberFormatException e) {
			Commons.dialog(new Retval(
					"Failed to parse your input. Make sure you are not typing letters in the place of numbers",
					false));
			return;
		}

		Retval retval = user.getBankAccount().deposit(moneyToDeposit);
		Commons.dialog(retval);
	}

	public static void call(User user) {
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();

		options.put("💸 Withdraw money", () -> withdrawMoney(user));
		options.put("💰 Deposit money", () -> depositMoney(user));

		menu("bank  account", options, true, true);
	}
}
