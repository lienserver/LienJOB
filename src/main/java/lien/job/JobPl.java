package lien.job;

import lien.job.api.Job;
import lien.job.command.JobCommand;
import lien.job.command.parameter.JobType;
import lien.job.command.parameter.StatType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.gnurung.gunucommandmodule.module.CommandProcesser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class JobPl extends JavaPlugin {
    private static JobPl inst = null;
    private CommandProcesser commandprocesser;
    public static JobPl getInstance(){
        return inst;
    }

    @Override
    public void onEnable() {
        commandprocesser = new CommandProcesser(JobCommand.class);
        commandprocesser.addParameter(new JobType());
        commandprocesser.addParameter(new StatType());
        inst = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private Map<String, Job> jobMap = new HashMap<>();

    public void addJob(Job job){
        jobMap.put(job.getName(),job);
    }

    public Job getJob(String name){
        return jobMap.get(name);
    }
    
    public Collection<Job> getJobs()
    {
    	return jobMap.values();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
    	commandprocesser.onCommand(sender, label, args);
    	return super.onCommand(sender, command, label, args);
    }
}