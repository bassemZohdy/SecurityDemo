package demo.service;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;
import org.springframework.web.util.UriComponents;

import demo.controller.MainController;
import demo.entity.Activation;
import demo.entity.User;
import demo.repo.UserRepo;

@Service
public class UserRegisterService {

	@Autowired
	private UserRepo repo;

	@Value("systemEmail")
	private String systemEmail;

	@Autowired
	private MailSender mailSender;

	public void register(User user) {
		user.setEnabled(false);
		user.setActivation(new Activation(user.getUsername(), UUID.randomUUID()
				.toString()));
		repo.save(user);
	}

	@Async
	public void sendActivation(User user) {
		UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodCall(
				on(MainController.class)
				.activate(user.getActivation().getToken())
				
				)
				.build();
		URI uri = uriComponents.encode().toUri();
		sendEmail({user.getEmail()}, "Activation", uri.toString());
	}

	public void activate(String token) {

	}

	private void sendEmail(String[] to, String subject, String body) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setReplyTo(systemEmail);
		mailMessage.setFrom(systemEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		mailSender.send(mailMessage);
	}

}
