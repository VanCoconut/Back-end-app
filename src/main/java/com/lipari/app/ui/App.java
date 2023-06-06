
package com.lipari.app.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lipari.app.config.ConfigurationBean;
import com.lipari.app.model.vo.Order;
import com.lipari.app.model.vo.Product;
import com.lipari.app.services.AdminService;
import com.lipari.app.services.OrderService;
import com.lipari.app.services.UserService;

public class App {

	private static boolean stop = true;
	private static boolean flag = true;
	private static boolean flag2 = true;


	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationBean.class);
		AdminService adminService = context.getBean(AdminService.class);
		OrderService orderService = context.getBean(OrderService.class);
		UserService userService = context.getBean(UserService.class);

		try {
			UserSessionManager userSessioManager = UserSessionManager.getInstance();			
			while (stop) {
				System.out.println("Menu di selezione funzionalità:\n" + "1) ELENCO ORDINI\n"
						+ "2) CREAZIONE NUOVO ORDINE\n" + "4) CANCELLAZIONE NUOVO ORDINE\n"
						+ "5) elenco di tutti gli ordini per utente di tipo admin\n" + "6) LOGIN\n"
						+ "Scrivere il numero");
				Scanner sc = new Scanner(System.in);
				int scelta = Integer.parseInt(sc.nextLine());
				switch (scelta) {
				case 1: {
					List<Order> l = orderService.retrieveAllOrders(userSessioManager.getUser().getId());
					// l.stream().map(e->oc.retrieveBasketXOrder(e.getId())).forEach(System.out::println);
					Iterator<Order> i = l.iterator();
					int counter = 1;
					while (i.hasNext()) {
						Order o = (Order) i.next();
						System.out.println("Ordine n." + counter + "   codice   " + o.getId());
						Map<Integer, String> map = orderService.retrieveBasketXOrder(o.getId());
						map.forEach((k, v) -> {
							System.out.println("Prodotto: " + v + " Quantita: " + k);
						});
						counter++;
					}

					System.out.println("INTERROMPERE : Y/N");
					if (sc.nextLine().equalsIgnoreCase("Y")) {
						stop = false;
					} else {
						continue;
					}
					break;
				}
				case 2: {

					while (flag2) {
						System.out.println("Inserire una data distante minimo 3 giorni nel formato YYYY-MM-DD");
						String data = sc.nextLine();
						DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
						LocalDate shippingDate = LocalDate.parse(data, formatter);
						LocalDate currentDate = LocalDate.now();
						long daysBetween = ChronoUnit.DAYS.between(currentDate, shippingDate);
						if(shippingDate==null || daysBetween<3) {
							System.out.println("Data non valida");
							continue;
						}
						System.out.println("Scegliere l'indirizzo tra le seguenti opzioni:");
						System.out.println(userService.adressList(userSessioManager.getUser().getId()));
						String indirizzo = sc.nextLine();
						if(orderService.findAddress(userSessioManager.getUser().getId(), indirizzo)) continue;
						flag2=false;
						String uuid = UUID.randomUUID().toString();
						orderService.addOrder(uuid, userSessioManager.getUser().getId(), shippingDate, indirizzo);

						while (flag) {
							System.out.println(orderService.retrieveAllProduct());
							System.out.println(
									"Inserire le caratteristiche del prodotto separate da invio: ID, QUANTITA\n");
							int orderId = Integer.parseInt(sc.nextLine());
							int q = Integer.parseInt(sc.nextLine());
							Product p = orderService.findProduct(orderId);
							if (p != null) {
								orderService.addProduct(uuid, orderId, q);
								System.out.println("Aggiungere altri prodotti?");
								if (sc.nextLine().equalsIgnoreCase("Y")) {
									continue;
								} else {
									flag = false;
								}
							} else {
								System.out.println("ID o QUANTITA' ERRATI, RIPROVARE?\n");
								if (sc.nextLine().equalsIgnoreCase("Y")) {
									continue;
								} else {
									flag = false;
								}
							}
						}
						System.out.println("INTERROMPERE : Y/N");
						if (sc.nextLine().equalsIgnoreCase("Y")) {
							stop = false;
						} else {
							continue;
						}
					}
					flag=true;
					flag2=true;
					break;
				}

				case 4: {
					System.out.println("Inserire id ordine da cancellare");
					String orderId = sc.nextLine();
					if (orderService.deleteOrder(orderId))
						System.out.println("Ordine eliminato");
					System.out.println("INTERROMPERE : Y/N");
					if (sc.nextLine().equalsIgnoreCase("Y")) {
						stop = false;
					} else {
						continue;
					}
					break;
				}
				case 5: {
					if (userSessioManager.getUser().getRole() == 1) {
						adminService.retrieveAdminOrders().forEach(System.out::println);
					} else {
						System.out.println("NON SEI AUTORIZZATO");
					}
					System.out.println("INTERROMPERE : Y/N");
					if (sc.nextLine().equalsIgnoreCase("Y")) {
						stop = false;
					} else {
						continue;
					}
					break;
				}
				case 6: {

					System.out.println("Inserire username e password separate da invio");
					userSessioManager.storeUser(userService.loging(sc.nextLine(), sc.nextLine()));
					if (userSessioManager.getUser() == null) {
						System.out.println("ACCESSO NEGATO");
					} else {
						System.out.println("ACCESSO CONSENTITO");
					}

					System.out.println("INTERROMPERE : Y/N");
					if (sc.nextLine().equalsIgnoreCase("Y")) {
						stop = false;
					} else {
						continue;
					}
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + scelta);
				}
			}

			System.out.println("programma chiuso");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
