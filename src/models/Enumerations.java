/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Elyes
 */
public class Enumerations {
    public enum Role{
	ADMIN,
	MEMBER
    }
    public enum BodyType{
	PLUTOT_NE_PAS_DIRE,
	MINCE,
	SURPOID,
	NORMAL,
	FORME,
	HERCULIEN,
	GROS
    }
    public enum MaritalStatus{
	CELIBATAIRE,
	MARRIE,
	VEUF,
	DIVORCE
    }
    public enum Religion{
	ISLAM,
	JUDAISME,
	CHRISTIANISME,
	ATHEISME,
	AGNOSTICISM
    }
    public enum RelationType{
	SERIEUSE,
	AMITIE,
	COURT_TERME
    }
    public enum Importance{
	INDIFFERENT,
	UN_PEU_IMPORTANT,
	IMPORTANT
    }
    public enum ReactionType{
	LIKE,
	SUPER_LIKE,
	LAUGH,
        SMILE,
        LOVE,
        SCOWL
    }
    public enum SignalReason{
	CONTENU_INAPPROPRIE,
	RACISME,
        Violence,
        Harcélement,
        Faux_Profil,
        Autre
        
    }
    public enum Proximity{
	PROCHE,
	DISTANT,
	BOTH
    }
    public enum LockedType{
	ENABLED,
	DISABLED, //l'utilisateur a désactivé son compte par lui meme
	BANNED
    }
    public enum NotificationType{
	MESSAGE,
	LIKE,
	REACTION,
	SIGNAL,
	FEEDBACK
    }
    
    public enum PostType{
        ANSWER,
        PICTURE,
        STATUS,
        UPDATE
    }
    public enum Topic{
	RELIGION,
	SEX,
	CULTURE,
	SPORT,
	GENERAL
    }
    public enum PhotoType{
        REGULAR,
        PROFILE,
        COVER
    }
}
